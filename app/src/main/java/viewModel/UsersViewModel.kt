package viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.practice3_jsonplaceholder.service.JSONPlaceholderApi
import kotlinx.coroutines.launch
import model.user.User

class UsersViewModel(private val jsonPlaceholderAPI: JSONPlaceholderApi) : ViewModel() {

    val users: MutableState<List<User>> = mutableStateOf<List<User>>(emptyList())

    fun fetchUsers() {
        viewModelScope.launch {
            users.value = jsonPlaceholderAPI.getUsers()
        }
    }

    fun getUserByUserId(userId: Int?): User? {
        return users.value.find { it.id == userId }
    }
}