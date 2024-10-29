package com.ovinkin.practice3_jsonplaceholder.data.repository

import com.ovinkin.practice3_jsonplaceholder.data.api.JSONPlaceholderApi
import com.ovinkin.practice3_jsonplaceholder.data.mapper.JSONPlaceholderResponseToEntityMapper
import com.ovinkin.practice3_jsonplaceholder.domain.model.CommentEntity
import com.ovinkin.practice3_jsonplaceholder.domain.model.PostEntity
import com.ovinkin.practice3_jsonplaceholder.domain.model.user.UserEntity
import com.ovinkin.practice3_jsonplaceholder.domain.repository.IJSONPlaceholderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JSONPlaceholderRepository(
    private val api: JSONPlaceholderApi, private val mapper: JSONPlaceholderResponseToEntityMapper
) : IJSONPlaceholderRepository {

    override suspend fun getPosts(): List<PostEntity> {
        return withContext(Dispatchers.IO) { mapper.mapPosts(api.getPosts()) }
    }

    override suspend fun getCommentsByPost(postId: Int): List<CommentEntity> {
        return withContext(Dispatchers.IO) { mapper.mapCommentsByPost(api.getCommentsByPost(postId)) }
    }

    override suspend fun getUserById(userId: Int): UserEntity {
        return withContext(Dispatchers.IO) { mapper.mapUserById(api.getUserById(userId)) }
    }
}