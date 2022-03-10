package com.beltranbetania.userposts.presentation.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beltranbetania.userposts.domain.model.User
import com.beltranbetania.userposts.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    val userModel = MutableLiveData<List<User>>()
    val isLoading = MutableLiveData<Boolean>()

    fun loadPosts() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getUsersUseCase()
            if (!result.isNullOrEmpty()) {
                userModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

}