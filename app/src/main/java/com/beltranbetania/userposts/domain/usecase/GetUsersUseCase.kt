package com.beltranbetania.userposts.domain.usecase

import com.beltranbetania.userposts.data.database.entities.toDatabase
import com.beltranbetania.userposts.data.repository.UserRepository
import com.beltranbetania.userposts.domain.model.User
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke():List<User>{
        var users = repository.getAllUsersFromDatabase()
        if (users.isEmpty()){
            users=  repository.getAllUsersFromApi()
            repository.insertUsers(users.map { it.toDatabase() })
        }
       return users
    }
}