package com.ovinkin.practice3_jsonplaceholder.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.practice3_jsonplaceholder.coroutinesUtils.launchLoadingAndError
import com.ovinkin.practice3_jsonplaceholder.domain.repository.IJSONPlaceholderRepository
import com.ovinkin.practice3_jsonplaceholder.presentation.mapper.JSONPlaceholderUIMapper
import com.ovinkin.practice3_jsonplaceholder.presentation.model.user.CompanyUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.model.user.UserUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.model.user.address.AddressUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.model.user.address.GeoUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.state.UserState

private val DEFAULT_USER = UserUiModel(
    id = 0,
    name = "Овинкин Кирилл",
    username = "RandoSky",
    email = "ovinkin.kir@gmail.com",
    address = AddressUiModel(
        street = "Мира", suite = "32", city = "Екатеринбург", zipCode = "635354", geo = GeoUiModel(
            lat = "60", lng = "56"
        )
    ),
    phone = "89999999999",
    website = "google.com",
    company = CompanyUiModel(
        name = "УрФУ",
        catchPhrase = "РТФ",
        bs = "Чемпион",
    ),
)

class UsersViewModel(
    private val repository: IJSONPlaceholderRepository, private val mapper: JSONPlaceholderUIMapper
) : ViewModel() {

    private val mutableState = MutableUserState()
    val viewState = mutableState as UserState

    init {
        fetchUsers()
    }

    suspend fun fetchUserById(userId: Int): UserUiModel {
        return mapper.mapUserById(repository.getUserById(userId))
    }

    private fun fetchUsers() {
        viewModelScope.launchLoadingAndError(handleError = {
            mutableState.error = it.localizedMessage
        }, updateLoading = { mutableState.loading = it }) {
            mutableState.users = emptyList()
            mutableState.error = null

            mutableState.users = mapper.mapUsers(repository.getUsers())
        }
    }

    private class MutableUserState : UserState {
        override var user: UserUiModel by mutableStateOf(DEFAULT_USER)
        override var users: List<UserUiModel> by mutableStateOf(emptyList())
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
    }
}