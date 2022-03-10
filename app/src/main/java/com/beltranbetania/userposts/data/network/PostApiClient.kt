package com.beltranbetania.userposts.data.network
import com.beltranbetania.userposts.data.model.PostModel
import com.beltranbetania.userposts.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApiClient {

    @GET("users")
    suspend fun getAllUsers(): Response<List<UserModel>>

    @GET("posts")
    suspend fun getPostByUserID(@Query("userId") id: Int): Response<List<PostModel>>


}