package com.ovinkin.practice3_jsonplaceholder.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.practice3_jsonplaceholder.domain.repository.IJSONPlaceholderRepository
import com.ovinkin.practice3_jsonplaceholder.presentation.mapper.JSONPlaceholderUIMapper
import com.ovinkin.practice3_jsonplaceholder.presentation.model.PostUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.state.PostsListState
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repository: IJSONPlaceholderRepository,
    private val mapper: JSONPlaceholderUIMapper,
) : ViewModel() {

    private val mutableState = MutablePostsListState()
    val viewState = mutableState as PostsListState

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
        }
    }

    private suspend fun fetchPostsFromNetwork(): List<PostUiModel> {
        try {
            val posts = mapper.mapPosts(repository.getPosts())
            mutableState.error = null

            return posts
        } catch (e: Exception) {
            mutableState.posts = emptyList()
            mutableState.error = e.localizedMessage
        }

        return emptyList()
    }

    fun filterPosts(userName: String?, postContent: String?) {
        viewModelScope.launch {
            val allPosts = fetchPostsFromNetwork()

            val filteredPosts = allPosts.filter { post ->
                val matchesUser = userName?.let {
                    repository.getUserById(post.userId).userName.contains(it, ignoreCase = true)
                } ?: true

                val matchesContent = postContent?.let {
                    post.title.contains(it, ignoreCase = true) || post.body.contains(
                        it, ignoreCase = true
                    )
                } ?: true

                matchesUser && matchesContent
            }

            mutableState.error = null
            mutableState.posts = filteredPosts
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