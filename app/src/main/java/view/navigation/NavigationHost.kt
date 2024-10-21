package view.navigation

import NavigationItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ovinkin.practice3_jsonplaceholder.service.JSONPlaceholderApi
import view.HomeScreen
import view.NewsScreen
import view.SettingsScreen
import viewModel.NewsViewModel

@Composable
fun NavigationHost(navController: NavHostController) {
    /** viewModel для страницы новостей */
    val newsViewModel = NewsViewModel(JSONPlaceholderApi())

    NavHost(navController, startDestination = NavigationItem.HomeScreen.route) {
        composable(NavigationItem.HomeScreen.route) {
            HomeScreen()
        }
        composable(NavigationItem.NewsScreen.route) {
            NewsScreen(viewModel = newsViewModel)
        }
        composable(NavigationItem.SettingsScreen.route) {
            SettingsScreen()
        }
    }
}