package com.ovinkin.practice3_jsonplaceholder.domain.model

data class CommentEntity(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)