package com.ovinkin.practice3_jsonplaceholder.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ovinkin.practice3_jsonplaceholder.storage.database.entity.PostsEntityDB

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<PostsEntityDB>

    @Query("SELECT * FROM posts WHERE postId = :postId")
    suspend fun getPostById(postId: Int): PostsEntityDB?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(posts: PostsEntityDB)

    @Query("DELETE FROM posts WHERE postId = :postId")
    suspend fun deletePost(postId: Int)
}