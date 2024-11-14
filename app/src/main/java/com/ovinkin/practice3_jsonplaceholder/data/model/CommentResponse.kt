package com.ovinkin.practice3_jsonplaceholder.data.model

import androidx.annotation.Keep

@Keep
data class CommentResponse(
    val postId: Int?,
    val id: Int?,
    val name: String?,
    val email: String?,
    val body: String?,
)