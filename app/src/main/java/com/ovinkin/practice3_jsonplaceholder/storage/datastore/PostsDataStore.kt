package com.ovinkin.practice3_jsonplaceholder.storage.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ovinkin.practice3_jsonplaceholder.presentation.model.SettingsUiModel
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "posts_data_store")

class PostsDataStore(private val context: Context) {

    companion object {
        private val USERNAME_FILTER_KEY = stringPreferencesKey("user_name_filter")
        private val POST_CONTENT_FILTER_KEY = stringPreferencesKey("post_content_filter")
    }

    suspend fun saveSettings(settingsData: SettingsUiModel) {
        context.dataStore.edit { pref ->
            pref[USERNAME_FILTER_KEY] = settingsData.usernameFilter
            pref[POST_CONTENT_FILTER_KEY] = settingsData.postContentFilter
        }
    }

    fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingsUiModel(
            usernameFilter = pref[USERNAME_FILTER_KEY] ?: "",
            postContentFilter = pref[POST_CONTENT_FILTER_KEY] ?: ""
        )
    }
}
