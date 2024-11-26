package com.ovinkin.practice3_jsonplaceholder.storage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ovinkin.practice3_jsonplaceholder.storage.database.utils.Constants

@Entity(tableName = Constants.NAME_TABLE_GROUPS)
data class PostsEntityDB(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo("userId") val userId: Int?,
    @ColumnInfo("postId") val postId: Int?,
    @ColumnInfo("title") val title: String?,
    @ColumnInfo("body") val body: String?,
)
