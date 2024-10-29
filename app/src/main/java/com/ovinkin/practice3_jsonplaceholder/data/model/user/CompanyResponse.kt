package com.ovinkin.practice3_jsonplaceholder.data.model.user

import androidx.annotation.Keep

@Keep
data class CompanyResponse(
    val name: String?,
    val catchPhrase: String?,
    val bs: String?,
)