package com.ovinkin.practice3_jsonplaceholder.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


// Тёмная цветовая схема
private val DarkColorScheme = darkColorScheme(
    primary = PastelWarmPink,
    secondary = PastelWarmPeach,
    tertiary = PastelWarmMint,
    background = DarkTextColor,
    surface = Color(0xFF424242), // Темная поверхность
    onPrimary = Color.White,
    onSecondary = DarkTextColor,
    onTertiary = Color.White,
    onSurface = Color.White,
    onBackground = LightTextColor,
)

// Светлая цветовая схема
private val LightColorScheme = lightColorScheme(
    primary = PastelWarmPink,
    secondary = PastelWarmPeach,
    tertiary = PastelWarmMint,
    background = Color.White,
    surface = Color.White,
    onPrimary = DarkTextColor,
    onSecondary = DarkTextColor,
    onTertiary = DarkTextColor,
    onSurface = DarkTextColor,
    onBackground = DarkTextColor,
)

@Composable
fun Practice3_JSONPlaceholderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}