package com.ovinkin.practice3_jsonplaceholder.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.practice3_jsonplaceholder.domain.repository.IJSONPlaceholderRepository
import com.ovinkin.practice3_jsonplaceholder.presentation.mapper.JSONPlaceholderUIMapper
import com.ovinkin.practice3_jsonplaceholder.presentation.model.PostUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.state.PostsListState
import com.ovinkin.practice3_jsonplaceholder.storage.database.dao.PostsDataBase
import com.ovinkin.practice3_jsonplaceholder.storage.database.entity.PostsEntityDB
import com.ovinkin.practice3_jsonplaceholder.storage.datastore.PostsDataStore
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repository: IJSONPlaceholderRepository,
    private val mapper: JSONPlaceholderUIMapper,
    private val postsDataStore: PostsDataStore,
    private val usersViewModel: UsersViewModel,
    private val dataBase: PostsDataBase,
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

    suspend fun getSettings() {
        postsDataStore.getSettings().collect { settings ->
            mutableState.usernameFilter = settings.usernameFilter
            mutableState.postContentFilter = settings.postContentFilter
        }
    }

    suspend fun isPostFavorite(postId: Int): Boolean {
        // Получаем пост из базы данных
        val post = dataBase.getDao().getPostById(postId)

        Log.w("post", post.toString())

        // Если пост найден, возвращаем true, если нет - false
        return post != null
    }

    suspend fun toggleFavorite(post: PostUiModel) {
        mutableState.isFavorite = !mutableState.isFavorite

        // Если пост в избранном, удаляем его из базы данных
        if (mutableState.isFavorite) {
            insertDBPost(post)
        } else {
            deleteDBPost(post)
        }
    }

    private suspend fun insertDBPost(post: PostUiModel) {
        dataBase.getDao().insertPost(
            PostsEntityDB(
                null, userId = post.userId, postId = post.id, title = post.title, body = post.body
            )
        )
    }

    private suspend fun deleteDBPost(post: PostUiModel) {
        dataBase.getDao().deletePost(postId = post.id)
    }

    private class MutablePostsListState : PostsListState {
        override var posts: List<PostUiModel> by mutableStateOf(emptyList())
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
        override var isFavorite: Boolean by mutableStateOf(false)
        override var usernameFilter: String by mutableStateOf("")
        override var postContentFilter: String by mutableStateOf("")
    }
}