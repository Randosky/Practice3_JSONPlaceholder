package com.ovinkin.practice3_jsonplaceholder.data.model.user.address

import androidx.annotation.Keep

@Keep
data class GeoResponse(
    val lat: String?,
    val lng: String?,
)