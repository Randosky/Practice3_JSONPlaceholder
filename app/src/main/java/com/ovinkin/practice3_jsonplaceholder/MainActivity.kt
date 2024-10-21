package com.ovinkin.practice3_jsonplaceholder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ovinkin.practice3_jsonplaceholder.ui.theme.Practice3_JSONPlaceholderTheme
import view.navigation.BottomBar
import view.navigation.NavigationHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Practice3_JSONPlaceholderTheme {
                ApplicationScreen()
            }
        }
    }

    @Preview
    @Composable
    fun ApplicationScreen() {
        val navController = rememberNavController()
        AppNavigation(navController)
    }

    @Composable
    fun AppNavigation(navigationController: NavHostController) {

        Scaffold(bottomBar = {
            BottomBar(
                navController = navigationController,
            )
        }) { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                NavigationHost(
                    navController = navigationController
                )
            }
        }
    }
}