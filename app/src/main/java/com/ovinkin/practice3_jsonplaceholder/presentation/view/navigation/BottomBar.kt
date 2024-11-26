package com.ovinkin.practice3_jsonplaceholder.presentation.view.navigation

import NavigationItem
import androidx.compose.foundation.layout.height
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import org.koin.androidx.compose.get
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ovinkin.practice3_jsonplaceholder.storage.cache.badgeCache.BadgeCache

@Composable
fun BottomBar(navController: NavHostController) {
    val badgeCache: BadgeCache = get()
    val badgeFilterCount by badgeCache.badgeFilterCount.collectAsState()

    val screens = listOf(
        NavigationItem.PostsScreen,
        NavigationItem.SettingsScreen,
        NavigationItem.FavouritesScreen,
    )

    BottomNavigation(modifier = Modifier.height(72.dp)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestinationRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            BottomNavigationItem(selected = currentDestinationRoute == screen.route, label = {
                Text(text = screen.title)
            }, icon = {
                BottomIcon(screen, badgeFilterCount)

            }, onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
    }
}

@Composable
fun BottomIcon(screen: NavigationItem, badgeFilterCount: Int) {

    if (screen == NavigationItem.SettingsScreen && badgeFilterCount != 0) {
        return BadgedBox(badge = { Badge { Text(badgeFilterCount.toString()) } }) {
            Icon(imageVector = screen.icon!!, contentDescription = "")
        }
    }

    Icon(imageVector = screen.icon!!, contentDescription = "")
}