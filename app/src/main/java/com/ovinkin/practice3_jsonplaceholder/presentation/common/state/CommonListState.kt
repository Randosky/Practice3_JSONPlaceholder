package com.ovinkin.practice3_jsonplaceholder.presentation.common.state

import androidx.compose.runtime.Stable

@Stable
interface CommonListState {
    val error: String?
    val loading: Boolean
}