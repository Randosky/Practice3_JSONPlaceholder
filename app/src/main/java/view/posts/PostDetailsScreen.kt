package view.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import model.Comment
import model.Post
import model.user.User
import viewModel.CommentsViewModel

@Composable
fun PostDetailsScreen(
    post: Post,
    userAuthor: User,
    commentsViewModel: CommentsViewModel,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        commentsViewModel.fetchCommentsByPost(post.id)
    }

    val comments = commentsViewModel.comments.value

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
                text = "Имя: ${userAuthor.name}",
                modifier = Modifier
                    .constrainAs(nameText) {
                        top.linkTo(title.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Пользователь: ${userAuthor.userName}",
                modifier = Modifier
                    .constrainAs(usernameText) {
                        top.linkTo(nameText.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Email: ${userAuthor.email}",
                modifier = Modifier
                    .constrainAs(emailText) {
                        top.linkTo(usernameText.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Телефон: ${userAuthor.phone}",
                modifier = Modifier
                    .constrainAs(phoneText) {
                        top.linkTo(emailText.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Вебсайт: ${userAuthor.website}",
                modifier = Modifier
                    .constrainAs(websiteText) {
                        top.linkTo(phoneText.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Компания: ${userAuthor.company.name}",
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
            modifier = Modifier.height(250.dp)
        ) {
            items(comments) { comment ->
                CommentItem(comment)
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
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