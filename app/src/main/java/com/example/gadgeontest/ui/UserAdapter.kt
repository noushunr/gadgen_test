package com.example.gadgeontest.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gadgeontest.R
import com.example.gadgeontest.data.model.Data
import com.example.gadgeontest.databinding.ItemUsersBinding
import com.example.gadgeontest.interfaces.UserClick
import java.util.List;

/**
 * Created by Noushad N on 06-06-2022.
 */
class UserAdapter(
    private var mContext: Context,
    private var items: MutableList<Data>?,
    var userClick: UserClick
) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.from(parent)

    fun submitList(items: kotlin.collections.List<Data>){
        var position = this.items?.size
        this.items?.addAll(items!!)
        notifyItemInserted(position!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var item = items!![position]
        holder.binding.data = item
        Glide.with(mContext)
            .load(item.avatar)
            .thumbnail(Glide.with(mContext).load(R.mipmap.ic_launcher))
            .into(holder.binding.ivUser)
        holder?.binding?.root?.setOnClickListener {
            userClick.onUserClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items?.size!!
    }

    class ViewHolder(val binding : ItemUsersBinding) : RecyclerView.ViewHolder(binding?.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUsersBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }


    }

}
