package com.ovinkin.practice3_jsonplaceholder.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.practice3_jsonplaceholder.coroutinesUtils.launchLoadingAndError
import com.ovinkin.practice3_jsonplaceholder.domain.repository.IJSONPlaceholderRepository
import com.ovinkin.practice3_jsonplaceholder.presentation.mapper.JSONPlaceholderUIMapper
import com.ovinkin.practice3_jsonplaceholder.presentation.model.PostUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.state.PostsListState

class PostsViewModel(
    private val repository: IJSONPlaceholderRepository, private val mapper: JSONPlaceholderUIMapper
) : ViewModel() {

    private val mutableState = MutablePostsListState()
    val viewState = mutableState as PostsListState

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.loading = it }) {
            mutableState.posts = emptyList()
            mutableState.error = null

            mutableState.posts = mapper.mapPosts(repository.getPosts())
        }
    }

    fun getPostById(postId: Int?): PostUiModel? {
        return viewState.posts.find { it.id == postId }
    }

    private class MutablePostsListState : PostsListState {
        override var posts: List<PostUiModel> by mutableStateOf(emptyList())
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
    }
}