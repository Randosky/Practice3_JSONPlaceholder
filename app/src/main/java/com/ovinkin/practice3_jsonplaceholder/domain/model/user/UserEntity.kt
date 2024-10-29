package com.ovinkin.practice3_jsonplaceholder.domain.model.user

import com.ovinkin.practice3_jsonplaceholder.domain.model.user.address.AddressEntity

data class UserEntity(
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val address: AddressEntity,
    val phone: String,
    val website: String,
    val company: CompanyEntity,
)