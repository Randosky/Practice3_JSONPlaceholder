package com.ovinkin.practice3_jsonplaceholder.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsUiModel(
    val userNameFilter: String,
    val postContentFilter: String,
)