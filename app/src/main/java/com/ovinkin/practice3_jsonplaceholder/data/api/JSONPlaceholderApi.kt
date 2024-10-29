package com.ovinkin.practice3_jsonplaceholder.data.api

import com.ovinkin.practice3_jsonplaceholder.data.model.CommentResponse
import com.ovinkin.practice3_jsonplaceholder.data.model.PostResponse
import com.ovinkin.practice3_jsonplaceholder.data.model.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JSONPlaceholderApi {
    @GET("posts")
    suspend fun getPosts(): List<PostResponse>

    @GET("comments")
    suspend fun getCommentsByPost(@Query("postId") postId: Int): List<CommentResponse>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): UserResponse
}