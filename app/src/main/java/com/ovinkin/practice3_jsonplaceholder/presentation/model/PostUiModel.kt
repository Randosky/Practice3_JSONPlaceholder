package com.ovinkin.practice3_jsonplaceholder.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class PostUiModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)