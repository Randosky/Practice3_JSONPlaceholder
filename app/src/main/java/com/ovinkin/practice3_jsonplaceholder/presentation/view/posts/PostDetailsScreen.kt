package com.ovinkin.practice3_jsonplaceholder.presentation.view.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.ovinkin.practice3_jsonplaceholder.presentation.model.CommentUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.model.PostUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.CommentsViewModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.UsersViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostDetailsScreen(
    post: PostUiModel,
    navController: NavController
) {

    val commentsViewModel = koinViewModel<CommentsViewModel>()
    val commentsState = commentsViewModel.viewState

    val userViewModel = koinViewModel<UsersViewModel>()
    userViewModel.fetchUserById(post.userId)
    val userState = userViewModel.viewState

    val lazyColumnState = rememberSaveable(saver = LazyListState.Saver) {
        LazyListState(
            0, 0
        )
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Назад")
        }

        Text(
            text = "Название:\n\n${post.title}",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Описание:\n\n${post.body}",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            val (title, nameText, usernameText, emailText, phoneText, websiteText, companyText) = createRefs()

            Text(
                text = "Информация об авторе",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(8.dp)
            )
            Text(
                text = "Имя: ${userState.user.name}",
                modifier = Modifier
                    .constrainAs(nameText) {
                        top.linkTo(title.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Пользователь: ${userState.user.userName}",
                modifier = Modifier
                    .constrainAs(usernameText) {
                        top.linkTo(nameText.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Email: ${userState.user.email}",
                modifier = Modifier
                    .constrainAs(emailText) {
                        top.linkTo(usernameText.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Телефон: ${userState.user.phone}",
                modifier = Modifier
                    .constrainAs(phoneText) {
                        top.linkTo(emailText.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Вебсайт: ${userState.user.website}",
                modifier = Modifier
                    .constrainAs(websiteText) {
                        top.linkTo(phoneText.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Компания: ${userState.user.company.name}",
                modifier = Modifier
                    .constrainAs(companyText) {
                        top.linkTo(websiteText.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 4.dp)
            )
        }

        Text(
            text = "Комментарии:",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 24.dp)
        )

        LazyColumn(
            modifier = Modifier.height(250.dp), lazyColumnState
        ) {
            items(commentsState.comments) { comment ->
                CommentItem(comment)
            }
        }
    }
}

@Composable
fun CommentItem(comment: CommentUiModel) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = comment.name,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            fontSize = 16.sp
        )
        Text(
            text = comment.body,
            color = Color.Black,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}