package com.ovinkin.practice3_jsonplaceholder.data.model.user.address

import androidx.annotation.Keep

@Keep
data class AddressResponse(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipCode: String?,
    val geo: GeoResponse?,
)

