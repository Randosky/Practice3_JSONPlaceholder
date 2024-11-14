package com.ovinkin.practice3_jsonplaceholder.presentation.view.navigation

import NavigationItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ovinkin.practice3_jsonplaceholder.presentation.view.FavouritesScreen
import com.ovinkin.practice3_jsonplaceholder.presentation.view.HomeScreen
import com.ovinkin.practice3_jsonplaceholder.presentation.view.SettingsScreen
import com.ovinkin.practice3_jsonplaceholder.presentation.view.posts.PostDetailsScreen
import com.ovinkin.practice3_jsonplaceholder.presentation.view.posts.PostsScreen
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.CommentsViewModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.PostsViewModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.UsersViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationHost(navController: NavHostController) {

    val postsViewModel = koinViewModel<PostsViewModel>()
    val userViewModel = koinViewModel<UsersViewModel>()
    val commentsViewModel = koinViewModel<CommentsViewModel>()

    NavHost(
        navController, startDestination = NavigationItem.HomeScreen.route
    ) {
        composable(NavigationItem.HomeScreen.route) {
            HomeScreen()
        }
        composable(NavigationItem.PostsScreen.route) {
            PostsScreen(postsViewModel, userViewModel, navController)
        }
        composable(NavigationItem.PostDetailsScreen.route) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")

            val post = postsViewModel.getPostById(postId?.toIntOrNull())

            if (post != null) {
                PostDetailsScreen(post, commentsViewModel, userViewModel, navController)
            }
        }
        composable(NavigationItem.FavouritesScreen.route) {
            FavouritesScreen()
        }
        composable(NavigationItem.SettingsScreen.route) {
            SettingsScreen(postsViewModel)
        }
    }
}