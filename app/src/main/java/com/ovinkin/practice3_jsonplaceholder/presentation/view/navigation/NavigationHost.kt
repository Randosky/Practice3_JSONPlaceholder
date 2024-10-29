package com.ovinkin.practice3_jsonplaceholder.presentation.view.navigation

import NavigationItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ovinkin.practice3_jsonplaceholder.presentation.view.HomeScreen
import com.ovinkin.practice3_jsonplaceholder.presentation.view.SettingsScreen
import com.ovinkin.practice3_jsonplaceholder.presentation.view.posts.PostDetailsScreen
import com.ovinkin.practice3_jsonplaceholder.presentation.view.posts.PostsScreen
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.PostsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationHost(navController: NavHostController) {

    NavHost(
        navController, startDestination = NavigationItem.HomeScreen.route
    ) {
        composable(NavigationItem.HomeScreen.route) {
            HomeScreen()
        }
        composable(NavigationItem.PostsScreen.route) {
            PostsScreen(navController)
        }
        composable(NavigationItem.PostDetailsScreen.route) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")

            val postsViewModel = koinViewModel<PostsViewModel>()

            val post = postsViewModel.getPostById(postId?.toIntOrNull())

            if (post != null) {
                PostDetailsScreen(post, navController)
            }
        }
        composable(NavigationItem.SettingsScreen.route) {
            SettingsScreen()
        }
    }
}