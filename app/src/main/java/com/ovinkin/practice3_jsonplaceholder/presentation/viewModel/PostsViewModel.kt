package com.ovinkin.practice3_jsonplaceholder.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.practice3_jsonplaceholder.domain.repository.IJSONPlaceholderRepository
import com.ovinkin.practice3_jsonplaceholder.presentation.datastore.PostsDataStore
import com.ovinkin.practice3_jsonplaceholder.presentation.mapper.JSONPlaceholderUIMapper
import com.ovinkin.practice3_jsonplaceholder.presentation.model.PostUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.state.PostsListState
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repository: IJSONPlaceholderRepository,
    private val mapper: JSONPlaceholderUIMapper,
    private val postsDataStore: PostsDataStore,
    private val usersViewModel: UsersViewModel,
) : ViewModel() {

    private val mutableState = MutablePostsListState()
    val viewState = mutableState as PostsListState

    init {
        viewModelScope.launch {
            fetchPostsFromNetwork()
        }
    }

    private suspend fun fetchPostsFromNetwork() {
        try {
            mutableState.posts = mapper.mapPosts(repository.getPosts())
            mutableState.error = null

        } catch (e: Exception) {
            mutableState.posts = emptyList()
            mutableState.error = e.localizedMessage
        }
    }

    suspend fun getSettings() {
        postsDataStore.getSettings().collect { settings ->
            mutableState.usernameFilter = settings.usernameFilter
            mutableState.postContentFilter = settings.postContentFilter
        }
    }

    fun filterPosts(): List<PostUiModel> {
        val allPosts = viewState.posts

        val usernameFilter = mutableState.usernameFilter.trim()
        val postContentFilter = mutableState.postContentFilter.trim()

        val filteredPosts = allPosts.filter { post ->
            val userById = usersViewModel.viewState.users.find { it.id == post.userId }

            // Проверяем, совпадает ли имя пользователя с фильтром (если фильтр не пустой)
            val matchesUser = if (usernameFilter.isNotEmpty()) {
                userById?.let { user ->
                    user.name.contains(usernameFilter, ignoreCase = true) || user.username.contains(
                        usernameFilter, ignoreCase = true
                    )
                } ?: false
            } else {
                true
            }

            // Проверяем, совпадает ли контент поста с фильтром (если фильтр не пустой)
            val matchesContent = if (postContentFilter.isNotEmpty()) {
                post.title.contains(postContentFilter, ignoreCase = true) || post.body.contains(
                    postContentFilter, ignoreCase = true
                )
            } else {
                true
            }

            // Пост должен соответствовать хотя бы одному из условий
            matchesUser && matchesContent
        }

        return filteredPosts
    }


    fun getPostById(postId: Int?): PostUiModel? {
        return viewState.posts.find { it.id == postId }
    }

    private class MutablePostsListState : PostsListState {
        override var posts: List<PostUiModel> by mutableStateOf(emptyList())
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
        override var usernameFilter: String by mutableStateOf("")
        override var postContentFilter: String by mutableStateOf("")
    }
}