package com.beltranbetania.userposts.domain.usecase

import com.beltranbetania.userposts.data.database.entities.toDatabase
import com.beltranbetania.userposts.data.repository.UserDetailRepository
import com.beltranbetania.userposts.domain.model.Post
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: UserDetailRepository) {
    suspend operator fun invoke(userId: Int):List<Post>{
        var users = repository.getPostsFromDatabase(userId)
        if (users.isEmpty()){
            users=  repository.getPostsFromApi(userId)
            repository.insertPosts(users.map { it.toDatabase() })
        }
        return users
    }
}