package com.ovinkin.practice3_jsonplaceholder.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.practice3_jsonplaceholder.coroutinesUtils.launchLoadingAndError
import com.ovinkin.practice3_jsonplaceholder.domain.repository.IJSONPlaceholderRepository
import com.ovinkin.practice3_jsonplaceholder.presentation.mapper.JSONPlaceholderUIMapper
import com.ovinkin.practice3_jsonplaceholder.presentation.model.CommentUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.state.CommentsListState

class CommentsViewModel(
    private val repository: IJSONPlaceholderRepository,
    private val mapper: JSONPlaceholderUIMapper
) : ViewModel() {

    private val mutableState = MutableCommentsListState()
    val viewState = mutableState as CommentsListState

    fun fetchCommentsByPost(postId: Int) {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.loading = it }) {
            mutableState.comments = emptyList()
            mutableState.error = null

            mutableState.comments = mapper.mapCommentsByPost(repository.getCommentsByPost(postId))
        }
    }

    private class MutableCommentsListState : CommentsListState {
        override var comments: List<CommentUiModel> by mutableStateOf(emptyList())
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
    }
}