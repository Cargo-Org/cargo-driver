package com.example.carog_driver.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class AppShapes(
    val extraSmall: RoundedCornerShape,
    val small: RoundedCornerShape,
    val medium: RoundedCornerShape,
    val large: RoundedCornerShape,
    val extraLarge: RoundedCornerShape,
    val pill: RoundedCornerShape,
)

val LightAppShapes = AppShapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp),
    pill = RoundedCornerShape(50.dp),
)

val DarkAppShapes = AppShapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(16.dp),
    medium = RoundedCornerShape(24.dp),
    large = RoundedCornerShape(32.dp),
    extraLarge = RoundedCornerShape(48.dp),
    pill = RoundedCornerShape(50.dp),
)

val LightMaterialShapes = Shapes(
    extraSmall = LightAppShapes.extraSmall,
    small = LightAppShapes.small,
    medium = LightAppShapes.medium,
    large = LightAppShapes.extraLarge,
    extraLarge = LightAppShapes.pill,
)

val DarkMaterialShapes = Shapes(
    extraSmall = DarkAppShapes.extraSmall,
    small = DarkAppShapes.small,
    medium = DarkAppShapes.medium,
    large = DarkAppShapes.extraLarge,
    extraLarge = DarkAppShapes.pill,
)

val LocalAppShapes = staticCompositionLocalOf { LightAppShapes }

