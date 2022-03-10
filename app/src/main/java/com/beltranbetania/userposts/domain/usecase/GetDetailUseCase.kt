package com.beltranbetania.userposts.domain.usecase

import com.beltranbetania.userposts.data.repository.UserDetailRepository
import com.beltranbetania.userposts.domain.model.User
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(private val repository: UserDetailRepository) {
    suspend operator fun invoke(userId: Int):User?{
        val user = repository.getUserByIdFromDatabase(userId)
        if (!user.isNullOrEmpty()){
            return user[0]
        }
        return null
    }
}