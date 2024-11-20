package com.ovinkin.practice3_jsonplaceholder.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.practice3_jsonplaceholder.presentation.datastore.PostsDataStore
import com.ovinkin.practice3_jsonplaceholder.presentation.model.SettingsUiModel
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val postsDataStore: PostsDataStore,
) : ViewModel() {

    var userNameFilter: String by mutableStateOf("")
    var postContentFilter: String by mutableStateOf("")

    init {
        viewModelScope.launch {
            getSettings()
        }
    }

    suspend fun getSettings() {
        postsDataStore.getSettings().collect { settings ->
            userNameFilter = settings.userNameFilter
            postContentFilter = settings.postContentFilter
        }
    }

    fun resetSettings() {
        setSettings("", "")
    }

    fun setSettings(userName: String, postContent: String) {
        viewModelScope.launch {
            saveSettings(userName, postContent)
        }
    }

    private suspend fun saveSettings(userName: String, postContent: String) {
        postsDataStore.saveSettings(
            settingsData = SettingsUiModel(
                userNameFilter = userName, postContentFilter = postContent
            )
        )
    }

}