package com.example.gadgeontest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gadgeontest.data.model.Data
import com.example.gadgeontest.data.repositories.UserRepositories
import java.lang.Exception

/**
 * Created by Noushad N on 06-06-2022.
 */
class UserViewModel( private val repository: UserRepositories) : ViewModel() {
    var error  = MutableLiveData<String>()
    var usersList = MutableLiveData<List<Data>>()
    var alUsers :MutableList<Data> = mutableListOf()
    var pageNo= 0
    var totalPages = 0

    suspend fun getList(){
        try {
            pageNo += 1
            val response = repository.getUserList(
                pageNo
            )
            if (response!=null){
                totalPages = response.totalPages!!
                alUsers.clear()
                alUsers.addAll(response.data!!)
                usersList.postValue(alUsers)

            }

        }catch (e: Exception){
            error.postValue("Check your network connection")
        }
    }
}