package com.beltranbetania.userposts.domain.usecase

import com.beltranbetania.userposts.data.database.entities.toDatabase
import com.beltranbetania.userposts.data.repository.UserDetailRepository
import com.beltranbetania.userposts.domain.model.Post
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: UserDetailRepository) {
    suspend operator fun invoke(userId: Int):List<Post>{
        var posts = repository.getPostsFromDatabase(userId)
        if (posts.isEmpty()){
            posts=  repository.getPostsFromApi(userId)
            repository.insertPosts(posts.map { it.toDatabase() })
        }
        return posts
    }
}