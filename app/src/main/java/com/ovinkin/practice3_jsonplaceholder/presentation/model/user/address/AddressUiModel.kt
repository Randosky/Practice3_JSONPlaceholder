package com.ovinkin.practice3_jsonplaceholder.presentation.model.user.address

import androidx.compose.runtime.Immutable

@Immutable
data class AddressUiModel(
    val street: String,
    val suite: String,
    val city: String,
    val zipCode: String,
    val geo: GeoUiModel,
)

