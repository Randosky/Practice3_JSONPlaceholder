package viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.practice3_jsonplaceholder.service.JSONPlaceholderApi
import kotlinx.coroutines.launch
import model.Post

class PostsViewModel(private val jsonPlaceholderAPI: JSONPlaceholderApi) : ViewModel() {

    val posts: MutableState<List<Post>> = mutableStateOf<List<Post>>(emptyList())

    fun fetchPosts() {
        viewModelScope.launch {
            posts.value = jsonPlaceholderAPI.getPosts()
        }
    }

    fun getPostById(postId: Int?): Post? {
        return posts.value.find { it.id == postId }
    }
}