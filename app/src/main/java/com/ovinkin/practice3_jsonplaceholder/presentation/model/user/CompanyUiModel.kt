package com.ovinkin.practice3_jsonplaceholder.presentation.model.user

import androidx.compose.runtime.Immutable

@Immutable
data class CompanyUiModel(
    val name: String,
    val catchPhrase: String,
    val bs: String,
)