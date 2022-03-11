package com.beltranbetania.userposts.presentation.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beltranbetania.userposts.domain.model.User
import com.beltranbetania.userposts.domain.usecase.GetUsersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersViewModelTest{
    @RelaxedMockK
    private lateinit var getUsersUseCase: GetUsersUseCase

    private lateinit var usersViewModel: UsersViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        usersViewModel = UsersViewModel(getUsersUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getUsersUseCase return a list set on the livedata`() = runTest {
        //Given
        val user = listOf(User(1, "ana", "Lorem@email.com","000", "www.ana.com"))
        coEvery { getUsersUseCase() } returns user

        //When
        usersViewModel.loadUsers()

        //Then
        assert(usersViewModel.userModel.value == user)
    }


}