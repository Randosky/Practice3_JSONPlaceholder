import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String, val title: String, val icon: ImageVector? = null
) {
    data object PostsScreen : NavigationItem(
        route = "posts", title = "Посты", icon = Icons.Outlined.Menu
    )

    data object SettingsScreen : NavigationItem(
        route = "settings", title = "Настройки", icon = Icons.Outlined.Settings
    )

    data object PostDetailsScreen : NavigationItem(
        route = "post_details/{postId}",
        title = "Детальная информация о посте"
    )

    data object FavouritesScreen : NavigationItem(
        route = "favourites",
        title = "Избранное",
        icon = Icons.Outlined.Favorite
    )
}