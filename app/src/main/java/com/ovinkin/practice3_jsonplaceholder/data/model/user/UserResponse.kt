package com.ovinkin.practice3_jsonplaceholder.data.model.user

import androidx.annotation.Keep
import com.ovinkin.practice3_jsonplaceholder.data.model.user.address.AddressResponse

@Keep
data class UserResponse(
    val id: Int?,
    val name: String?,
    val userName: String?,
    val email: String?,
    val address: AddressResponse?,
    val phone: String?,
    val website: String?,
    val company: CompanyResponse?,
)