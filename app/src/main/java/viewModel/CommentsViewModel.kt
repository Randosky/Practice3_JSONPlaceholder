package viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.practice3_jsonplaceholder.service.JSONPlaceholderApi
import kotlinx.coroutines.launch
import model.Comment

class CommentsViewModel(private val jsonPlaceholderAPI: JSONPlaceholderApi) : ViewModel() {

    val comments: MutableState<List<Comment>> = mutableStateOf<List<Comment>>(emptyList())

    fun fetchCommentsByPost(postId: Int) {
        viewModelScope.launch {
            comments.value = jsonPlaceholderAPI.getCommentsByPost(postId)
        }
    }
}