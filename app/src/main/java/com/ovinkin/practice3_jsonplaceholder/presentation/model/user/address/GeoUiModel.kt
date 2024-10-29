package com.ovinkin.practice3_jsonplaceholder.presentation.model.user.address

import androidx.compose.runtime.Immutable

@Immutable
data class GeoUiModel(
    val lat: String,
    val lng: String,
)