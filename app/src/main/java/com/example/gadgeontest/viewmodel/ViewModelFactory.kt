package com.example.gadgeontest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gadgeontest.data.repositories.UserRepositories

/*
 *Created by Noushad N on 05-06-2023
*/

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: UserRepositories
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }

}