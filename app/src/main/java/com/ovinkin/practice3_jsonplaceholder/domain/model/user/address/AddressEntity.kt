package com.ovinkin.practice3_jsonplaceholder.domain.model.user.address

data class AddressEntity(
    val street: String,
    val suite: String,
    val city: String,
    val zipCode: String,
    val geo: GeoEntity,
)

