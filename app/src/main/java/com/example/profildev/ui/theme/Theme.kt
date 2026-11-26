package com.example.profildev.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = Bleu,
    onPrimary = Color.White,
    primaryContainer = BleuPale,
    onPrimaryContainer = BleuFonce,
    secondary = Color(0xFF526079),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD9E3F8),
    onSecondaryContainer = Color(0xFF152037),
    background = FondClair,
    onBackground = TexteSurClair,
    surface = Color.White,
    onSurface = TexteSurClair,
    surfaceVariant = Color(0xFFE8EDF7),
    onSurfaceVariant = Color(0xFF414755),
    outline = Color(0xFF747985)
)

private val DarkColorScheme = darkColorScheme(
    primary = BleuClair,
    onPrimary = BleuFonce,
    primaryContainer = Color(0xFF254677),
    onPrimaryContainer = BleuPale,
    secondary = Color(0xFFAFC4E8),
    onSecondary = Color(0xFF203047),
    secondaryContainer = Color(0xFF35455E),
    onSecondaryContainer = Color(0xFFD9E3F8),
    background = FondSombre,
    onBackground = TexteSurSombre,
    surface = Color(0xFF181E28),
    onSurface = TexteSurSombre,
    surfaceVariant = Color(0xFF252D3B),
    onSurfaceVariant = Color(0xFFC4CBD7),
    outline = Color(0xFF8E95A2)
)

@Composable
fun ProfilDevTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Le theme suit le reglage clair ou sombre du telephone
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes(
            small = RoundedCornerShape(8.dp),
            medium = RoundedCornerShape(12.dp),
            large = RoundedCornerShape(16.dp)
        ),
        content = content
    )
}
