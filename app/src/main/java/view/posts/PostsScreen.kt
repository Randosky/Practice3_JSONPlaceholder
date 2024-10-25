package view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ovinkin.practice3_jsonplaceholder.R
import model.Post
import model.user.User
import viewModel.PostsViewModel
import viewModel.UsersViewModel

@Composable
fun PostsScreen(
    postsViewModel: PostsViewModel, usersViewModel: UsersViewModel, navController: NavHostController
) {

    LaunchedEffect(Unit) {
        postsViewModel.fetchPosts()
        usersViewModel.fetchUsers()
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

        LazyColumn {
            items(postsViewModel.posts.value) { post ->
                val user = usersViewModel.getUserByUserId(post.userId)

                PostItem(post, user) {
                    navController.navigate("post_details/${post.id}")
                }
            }
        }
    }
}

@Composable
fun PostItem(post: Post, user: User?, onPostClick: () -> Unit) {
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

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Post ID: ${post.id}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .weight(1f)
                )

                Text(
                    text = "Автор: ${user?.userName}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .weight(1f)
                )
            }
        }
    }

}