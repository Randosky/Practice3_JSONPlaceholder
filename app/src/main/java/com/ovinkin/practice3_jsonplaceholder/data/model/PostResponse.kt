package com.ovinkin.practice3_jsonplaceholder.data.model

import androidx.annotation.Keep

@Keep
data class PostResponse(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val body: String?,
)