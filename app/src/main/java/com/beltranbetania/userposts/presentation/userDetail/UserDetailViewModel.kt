package com.beltranbetania.userposts.presentation.userDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beltranbetania.userposts.core.CoroutineContextProvider
import com.beltranbetania.userposts.domain.model.Post
import com.beltranbetania.userposts.domain.model.User
import com.beltranbetania.userposts.domain.usecase.GetDetailUseCase
import com.beltranbetania.userposts.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class UserDetailViewModel @Inject constructor(contextProvider: CoroutineContextProvider,
                                              private var getDetailUseCase: GetDetailUseCase,
                                              private var getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    val userModel = MutableLiveData<User>()
    val postModel = MutableLiveData<List<Post>>()
    val isLoading = MutableLiveData<Boolean>()
    val ioContext: CoroutineContext = (contextProvider.IO)

    fun getDetailPost(postId: Int) {
        viewModelScope.launch(ioContext) {
            isLoading.postValue(true)
            val tasks = listOf(
                async(ioContext) {
                    val result = getDetailUseCase.invoke(postId)
                    userModel.postValue(result!!)},
                async(ioContext) {
                    val resultComment = getPostsUseCase.invoke(postId)
                    postModel.postValue(resultComment)}
            )
            tasks.awaitAll()
            isLoading.postValue(false)
        }
    }


}