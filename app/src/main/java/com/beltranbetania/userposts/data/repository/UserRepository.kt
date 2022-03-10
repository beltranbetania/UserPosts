package com.beltranbetania.userposts.data.repository

import com.beltranbetania.userposts.data.database.dao.PostDao
import com.beltranbetania.userposts.data.database.entities.UserEntity
import com.beltranbetania.userposts.data.model.UserModel
import com.beltranbetania.userposts.data.network.PostService
import com.beltranbetania.userposts.domain.model.User
import com.beltranbetania.userposts.domain.model.toDomain
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val api: PostService,
    private val postDao: PostDao
) {

    suspend fun getAllUsersFromApi(): List<User> {
        val response: List<UserModel> = api.getUsers()
        return response.map { it.toDomain() }
    }

    suspend fun getAllUsersFromDatabase():List<User>{
        val response: List<UserEntity> = postDao.getAllUsers()
        return response.map { it.toDomain() }
    }

    suspend fun insertUsers(users:List<UserEntity>){
        postDao.insertAllUsers(users)
    }

}