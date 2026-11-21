package com.example.enarah.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val EnarahLightColorScheme = lightColorScheme(
    primary = TealPrimary,
    onPrimary = Color.White,
    primaryContainer = TealLightTint,
    onPrimaryContainer = TealPrimary,
    secondary = AmberAccent,
    onSecondary = TextPrimary,
    background = BackgroundLight,
    onBackground = TextPrimary,
    surface = SurfaceWhite,
    onSurface = TextPrimary,
    outline = BorderSubtle
)

@Composable
fun EnarahTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EnarahLightColorScheme,
        typography = EnarahTypography,
        content = content
    )
}