package com.example.devbox.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DevBoxDarkColors = darkColorScheme(

    primary =
        Color(0xFF00E5FF),

    secondary =
        Color(0xFF7C4DFF),

    tertiary =
        Color(0xFF00C853),

    background =
        Color(0xFF0D1117),

    surface =
        Color(0xFF161B22),

    surfaceVariant =
        Color(0xFF21262D),

    onPrimary =
        Color.Black,

    onBackground =
        Color(0xFFE6EDF3),

    onSurface =
        Color(0xFFE6EDF3)
)

@Composable
fun DevBoxTheme(

    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme =
            DevBoxDarkColors,

        typography =
            Typography,

        content = content
    )
}