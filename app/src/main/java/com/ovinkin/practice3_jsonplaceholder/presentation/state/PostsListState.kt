package com.ovinkin.practice3_jsonplaceholder.presentation.state

import androidx.compose.runtime.Stable
import com.ovinkin.practice3_jsonplaceholder.presentation.common.state.CommonListState
import com.ovinkin.practice3_jsonplaceholder.presentation.model.PostUiModel

@Stable
interface PostsListState : CommonListState {
    val posts: List<PostUiModel>
}