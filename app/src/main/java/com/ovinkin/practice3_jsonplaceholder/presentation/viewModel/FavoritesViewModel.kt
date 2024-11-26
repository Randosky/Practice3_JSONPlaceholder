package com.ovinkin.practice3_jsonplaceholder.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ovinkin.practice3_jsonplaceholder.presentation.model.PostUiModel
import com.ovinkin.practice3_jsonplaceholder.storage.database.dao.PostsDataBase
import com.ovinkin.practice3_jsonplaceholder.storage.database.mapper.DataBasePostsMapper

class FavoritesViewModel(
    private val dataBase: PostsDataBase,
    private val databaseMapper: DataBasePostsMapper,
) : ViewModel() {
    private val mutablePosts: List<PostUiModel> by mutableStateOf(emptyList())
    val viewState = mutablePosts

    suspend fun getAllPosts(): List<PostUiModel> {
        return dataBase.getDao().getAllPosts().mapNotNull { databaseMapper.mapToUIPosts(it) }
    }
}