package com.ovinkin.practice3_jsonplaceholder.presentation.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.ovinkin.practice3_jsonplaceholder.presentation.model.PostUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Создаем расширение для DataStore
private val Context.dataStore by preferencesDataStore(name = "posts_preferences")

class PostsDataStore(private val context: Context) {

    companion object {
        private val USERNAME_KEY = stringPreferencesKey("filter_user_name")
        private val POST_CONTENT_KEY = stringPreferencesKey("filter_post_content")
        private val FILTERED_POSTS_KEY = stringPreferencesKey("filtered_posts")
    }

    // Получение текущих значений фильтров из DataStore
    val userNameFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USERNAME_KEY]
    }

    val postContentFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[POST_CONTENT_KEY]
    }

    val filteredPostsFlow: Flow<List<PostUiModel>> = context.dataStore.data.map { preferences ->
        val json = preferences[FILTERED_POSTS_KEY] ?: return@map emptyList()
        Gson().fromJson(json, Array<PostUiModel>::class.java).toList()
    }

    // Функция для сохранения фильтров
    suspend fun saveFilters(userName: String?, postContent: String?) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = userName ?: ""
            preferences[POST_CONTENT_KEY] = postContent ?: ""
        }
    }

    // Функция для сохранения отфильтрованных постов
    suspend fun saveFilteredPosts(posts: List<PostUiModel>) {
        context.dataStore.edit { preferences ->
            val json = Gson().toJson(posts)
            preferences[FILTERED_POSTS_KEY] = json
        }
    }
}
