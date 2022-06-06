package com.example.gadgeontest.data.repositories

import com.example.gadgeontest.data.model.User
import com.example.gadgeontest.data.network.MyApi
import com.example.gadgeontest.data.network.SafeApiRequest

/**
 * Created by Noushad N on 06-06-2022.
 */
class UserRepositories( private val api: MyApi) : SafeApiRequest()  {

    suspend fun getUserList(pageNo :Int):User{

        return apiRequest {api?.getUsersList(pageNo)}
    }
}