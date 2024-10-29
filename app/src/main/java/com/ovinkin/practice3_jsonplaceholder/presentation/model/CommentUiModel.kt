package com.ovinkin.practice3_jsonplaceholder.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class CommentUiModel(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)