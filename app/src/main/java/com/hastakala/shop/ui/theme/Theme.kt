package com.hastakala.shop.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = SoftBrown,
    onPrimary = WarmCream,
    secondary = ClayOrange,
    onSecondary = WarmCream,
    tertiary = OliveGreen,
    background = WarmCream,
    onBackground = DeepBrown,
    surface = CardCream,
    onSurface = DeepBrown,
    surfaceVariant = ColorVariant.SurfaceVariant,
    onSurfaceVariant = ColorVariant.OnSurfaceVariant,
    outline = ColorVariant.Outline
)

@Composable
fun HastaKalaShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}

private object ColorVariant {
    val SurfaceVariant = androidx.compose.ui.graphics.Color(0xFFF4E3CE)
    val OnSurfaceVariant = androidx.compose.ui.graphics.Color(0xFF765743)
    val Outline = androidx.compose.ui.graphics.Color(0xFFC8A98E)
}
