package com.basta.demo.presentation.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val CustomColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    onSecondary = Green80,
    background = Black100,
)

@Composable
fun DemoAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CustomColorScheme,
        typography = Typography,
        content = content
    )
}