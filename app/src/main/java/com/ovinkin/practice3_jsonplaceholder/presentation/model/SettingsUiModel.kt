package com.ovinkin.practice3_jsonplaceholder.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsUiModel(
    val usernameFilter: String,
    val postContentFilter: String,
)