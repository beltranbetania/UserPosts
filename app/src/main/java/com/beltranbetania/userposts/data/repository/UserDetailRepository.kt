package com.beltranbetania.userposts.data.repository

import com.beltranbetania.userposts.data.database.dao.PostDao
import com.beltranbetania.userposts.data.database.entities.PostEntity
import com.beltranbetania.userposts.data.database.entities.UserEntity
import com.beltranbetania.userposts.data.model.PostModel
import com.beltranbetania.userposts.data.network.PostService
import com.beltranbetania.userposts.domain.model.Post
import com.beltranbetania.userposts.domain.model.User
import com.beltranbetania.userposts.domain.model.toDomain
import javax.inject.Inject

class UserDetailRepository @Inject constructor(
    private val api: PostService,
    private val postDao: PostDao
) {
    suspend fun getPostsFromApi(id:Int): List<Post> {
        val response: List<PostModel> = api.getPostsByUserId(id)
        return response.map { it.toDomain() }
    }

    suspend fun getPostsFromDatabase(id:Int):List<Post>{
        val response: List<PostEntity> = postDao.getPostsByUser(id)
        return response.map { it.toDomain() }
    }

    suspend fun getUserByIdFromDatabase(id:Int):List<User>{
        val response :  List<UserEntity> = postDao.getUserById(id)
        return response.map { it.toDomain() }
    }

    suspend fun insertPosts(posts:List<PostEntity>){
        postDao.insertAllPosts(posts)
    }

}