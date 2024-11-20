package com.ovinkin.practice3_jsonplaceholder.presentation.model.user

import androidx.compose.runtime.Immutable
import com.ovinkin.practice3_jsonplaceholder.presentation.model.user.address.AddressUiModel

@Immutable
data class UserUiModel(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressUiModel,
    val phone: String,
    val website: String,
    val company: CompanyUiModel,
)