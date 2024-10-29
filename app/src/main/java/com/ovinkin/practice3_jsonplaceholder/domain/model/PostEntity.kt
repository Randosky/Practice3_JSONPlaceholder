package com.ovinkin.practice3_jsonplaceholder.domain.model

data class PostEntity(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)