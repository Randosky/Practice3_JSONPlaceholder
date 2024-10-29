package com.ovinkin.practice3_jsonplaceholder.domain.repository

import com.ovinkin.practice3_jsonplaceholder.domain.model.CommentEntity
import com.ovinkin.practice3_jsonplaceholder.domain.model.PostEntity
import com.ovinkin.practice3_jsonplaceholder.domain.model.user.UserEntity

interface IJSONPlaceholderRepository {

    suspend fun getPosts(): List<PostEntity>

    suspend fun getCommentsByPost(postId: Int): List<CommentEntity>

    suspend fun getUserById(userId: Int): UserEntity
}