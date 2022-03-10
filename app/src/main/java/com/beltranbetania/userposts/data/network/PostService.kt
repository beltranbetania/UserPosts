package com.beltranbetania.userposts.data.network

import com.beltranbetania.userposts.data.model.PostModel
import com.beltranbetania.userposts.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


class PostService @Inject constructor(private val api:PostApiClient) {

    suspend fun getUsers(): List<UserModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllUsers()
                response.body() ?: emptyList()
            }catch (e:Exception){
                emptyList()
            }

        }
    }

    suspend fun getPostsByUserId(id:Int): List<PostModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getPostByUserID(id)
                response.body() ?: emptyList()
            }catch (e:Exception){
                emptyList()
            }
        }
    }





}