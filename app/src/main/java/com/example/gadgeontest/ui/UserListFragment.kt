package com.example.gadgeontest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gadgeontest.R
import com.example.gadgeontest.data.model.Data
import com.example.gadgeontest.databinding.FragmentUserListBinding
import com.example.gadgeontest.interfaces.UserClick
import com.example.gadgeontest.viewmodel.UserViewModel
import com.example.gadgeontest.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private lateinit var viewModel: UserViewModel
    private val factory: ViewModelFactory by instance()
    private lateinit var binding : FragmentUserListBinding
    private lateinit var navController: NavController
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserListBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        var adapter = UserAdapter(requireContext(), mutableListOf(),object : UserClick{
            override fun onUserClick(data: Data) {
                var action = UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(data)
                navController.navigate(action)
            }

        })
        binding?.rvUsers.adapter = adapter
        getUserList()
        viewModel?.usersList.observe(viewLifecycleOwner) {
            binding?.progressCircular.visibility = View.GONE
            adapter.submitList(it!!)
        }
        binding?.rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (viewModel?.pageNo<viewModel.totalPages){
                        getUserList()
                    }
                }
            }
        })

    }
    fun getUserList(){
        binding?.progressCircular.visibility = View.VISIBLE
        lifecycleScope?.launch {
            viewModel?.getList()
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}