package com.ovinkin.practice3_jsonplaceholder.storage.database.mapper

import com.ovinkin.practice3_jsonplaceholder.presentation.model.PostUiModel
import com.ovinkin.practice3_jsonplaceholder.storage.database.entity.PostsEntityDB

class DataBasePostsMapper {

    // Преобразование списка PostUiModel в список PostsEntityDB
    fun mapToDatabasePosts(post: PostUiModel): PostsEntityDB {
        return PostsEntityDB(
            id = null, userId = post.userId, postId = post.id, title = post.title, body = post.body
        )

    }

    // Преобразование списка PostsEntityDB в список PostUiModel
    fun mapToUIPosts(post: PostsEntityDB): PostUiModel? {
        if (post.postId != null && post.userId != null && post.title != null && post.body != null) {
            return PostUiModel(
                id = post.postId, userId = post.userId, title = post.title, body = post.body
            )
        }

        return null
    }
}
