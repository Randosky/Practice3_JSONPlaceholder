package com.ovinkin.practice3_jsonplaceholder.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.practice3_jsonplaceholder.storage.datastore.PostsDataStore
import com.ovinkin.practice3_jsonplaceholder.presentation.model.SettingsUiModel
import com.ovinkin.practice3_jsonplaceholder.storage.cache.badgeCache.BadgeCache
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val postsDataStore: PostsDataStore,
    private val badgeCache: BadgeCache,
) : ViewModel() {

    var usernameFilter: String by mutableStateOf("")
    var postContentFilter: String by mutableStateOf("")

    init {
        viewModelScope.launch {
            getSettings()
        }
    }

    suspend fun getSettings() {
        postsDataStore.getSettings().collect { settings ->
            usernameFilter = settings.usernameFilter
            postContentFilter = settings.postContentFilter

            badgeCache.updateBadgeFilterCount(
                settings.usernameFilter, settings.postContentFilter
            )
        }
    }

    fun resetSettings() {
        setSettings("", "")
    }

    fun setSettings(username: String, postContent: String) {
        viewModelScope.launch {
            saveSettings(username, postContent)
        }
    }

    private suspend fun saveSettings(username: String, postContent: String) {
        postsDataStore.saveSettings(
            settingsData = SettingsUiModel(
                usernameFilter = username, postContentFilter = postContent
            )
        )
    }

}