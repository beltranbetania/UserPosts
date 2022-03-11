package com.beltranbetania.userposts.domain.usecase

import com.beltranbetania.userposts.data.repository.UserRepository
import com.beltranbetania.userposts.domain.model.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class getUsersUserCaseTest{

    @RelaxedMockK
    private lateinit var userRepository: UserRepository

    lateinit var getUsersUserCase: GetUsersUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getUsersUserCase = GetUsersUseCase(userRepository)
    }

    @Test
    fun `when the database doesnt return anything then get values from api`() = runBlocking {
        //Given
        coEvery { userRepository.getAllUsersFromDatabase() } returns emptyList()

        //When
        getUsersUserCase()

        //Then
        coVerify(exactly = 1) { userRepository.getAllUsersFromApi()
                                userRepository.insertUsers(any())
        }
    }

    @Test
    fun `when the db return data`() = runBlocking {
        //Given
        val myList = listOf(User(1, "ana", "Lorem@email.com","000", "www.ana.com"))
        // coEvery is for coroutine else just every
        coEvery { userRepository.getAllUsersFromDatabase() } returns myList

        //When
        val response = getUsersUserCase()

        coVerify(exactly = 0) { userRepository.getAllUsersFromApi() }

        assert(response == myList)
    }

}

