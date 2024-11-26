package com.ovinkin.practice3_jsonplaceholder.presentation.view.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ovinkin.practice3_jsonplaceholder.R
import com.ovinkin.practice3_jsonplaceholder.presentation.model.PostUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.PostsViewModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.UsersViewModel

@Composable
fun PostsScreen(
    postsViewModel: PostsViewModel, userViewModel: UsersViewModel, navController: NavHostController
) {

    val postsState = postsViewModel.viewState
    val userState = userViewModel.viewState

    var posts by remember { mutableStateOf(postsState.posts) }

    val lazyColumnState = rememberSaveable(saver = LazyListState.Saver) {
        LazyListState(
            0, 0
        )
    }

    LaunchedEffect(postsState.posts) {
        posts = postsState.posts
    }

    LaunchedEffect(Unit) {
        postsViewModel.getSettings()
    }

    LaunchedEffect(Unit, postsState.usernameFilter, postsState.postContentFilter) {
        posts = postsViewModel.filterPosts()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            text = stringResource(id = R.string.posts_screen),
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (postsState.loading || userState.loading) {
            CircularProgressIndicator()
        } else if (postsState.error !== null) {
            postsState.error?.let { errorMessage ->
                Text(
                    text = "Ошибка загрузки данных постов: $errorMessage",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize(), lazyColumnState) {
                if (posts.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Посты не найдены",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                } else {
                    // Отображаем список постов, если они есть
                    items(posts) { post ->
                        PostItem(post, userViewModel) {
                            navController.navigate("post_details/${post.id}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(
    post: PostUiModel, userViewModel: UsersViewModel, onPostClick: () -> Unit
) {
    val username = remember { mutableStateOf("") }

    // Получение имени пользователя по userId
    LaunchedEffect(post.userId) {
        try {
            val user = userViewModel.fetchUserById(post.userId)
            username.value = user.name
        } catch (e: Exception) {
            username.value = "Неизвестный"
        }
    }

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onPostClick() },
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Название:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = post.title,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Описание:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = post.body, style = MaterialTheme.typography.bodyMedium, fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Post ID: ${post.id}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
                Text(
                    text = username.value,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}