package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val CrimsonArenaDarkColorScheme = darkColorScheme(
    primary = CrimsonPrimary,
    onPrimary = TextPrimary,
    primaryContainer = CrimsonMuted,
    onPrimaryContainer = TextPrimary,
    secondary = ChampionshipGold,
    onSecondary = ObsidianBlack,
    secondaryContainer = GoldMuted,
    onSecondaryContainer = ChampionshipGold,
    tertiary = MetallicGold,
    onTertiary = ObsidianBlack,
    background = ObsidianBlack,
    onBackground = TextPrimary,
    surface = CharcoalSurface,
    onSurface = TextPrimary,
    surfaceVariant = CharcoalElevated,
    onSurfaceVariant = TextSecondary,
    surfaceContainerLowest = ObsidianBlack,
    surfaceContainerLow = CharcoalSurface,
    surfaceContainer = CharcoalCard,
    surfaceContainerHigh = CharcoalElevated,
    outline = CharcoalBorder,
    outlineVariant = CharcoalBorder,
    error = CrimsonError,
    onError = TextPrimary
)

val CrimsonArenaShapes = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(14.dp),
    large = RoundedCornerShape(18.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

@Composable
fun CrimsonArenaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CrimsonArenaDarkColorScheme,
        typography = CrimsonArenaTypography,
        shapes = CrimsonArenaShapes,
        content = content
    )
}
