package view.navigation

import NavigationItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ovinkin.practice3_jsonplaceholder.service.JSONPlaceholderApi
import view.HomeScreen
import view.PostsScreen
import view.SettingsScreen
import view.posts.PostDetailsScreen
import viewModel.CommentsViewModel
import viewModel.PostsViewModel
import viewModel.UsersViewModel

@Composable
fun NavigationHost(navController: NavHostController) {

    val repositoryAPI = JSONPlaceholderApi()

    /** viewModel для страницы новостей */
    val postsViewModel = PostsViewModel(repositoryAPI)

    /** viewModel для пользователей */
    val usersViewModel = UsersViewModel(repositoryAPI)

    /** viewModel для комментарие */
    val commentsViewModel = CommentsViewModel(repositoryAPI)

    NavHost(
        navController, startDestination = NavigationItem.HomeScreen.route
    ) {
        composable(NavigationItem.HomeScreen.route) {
            HomeScreen()
        }
        composable(NavigationItem.PostsScreen.route) {
            PostsScreen(postsViewModel, usersViewModel, navController)
        }
        composable(NavigationItem.PostDetailsScreen.route) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
            val post = postsViewModel.getPostById(postId?.toIntOrNull())
            val userAuthor = usersViewModel.getUserByUserId(post?.userId)

            if (post != null && userAuthor != null) {
                PostDetailsScreen(post, userAuthor, commentsViewModel, navController)
            }
        }
        composable(NavigationItem.SettingsScreen.route) {
            SettingsScreen()
        }
    }
}