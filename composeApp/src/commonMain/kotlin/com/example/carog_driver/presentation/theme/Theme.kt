package com.example.carog_driver.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.foundation.isSystemInDarkTheme

private val LocalIsDarkTheme = compositionLocalOf { false }

object AppTheme {
    val colors
        @Composable @ReadOnlyComposable get() = if (LocalIsDarkTheme.current) DarkColorScheme else LightColorScheme

    val extraColors
        @Composable @ReadOnlyComposable get() = LocalThemeExtraColors.current

    val typography
        @Composable @ReadOnlyComposable get() = if (LocalIsDarkTheme.current) DarkAppTypography else LightAppTypography

    val shapes
        @Composable @ReadOnlyComposable get() = if (LocalIsDarkTheme.current) DarkAppShapes else LightAppShapes

    val dimens
        @Composable @ReadOnlyComposable get() = LocalAppDimensions.current
}

@Composable
fun CargoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extraColors = if (darkTheme) DarkExtraColors else LightExtraColors
    val typography = if (darkTheme) DarkAppTypography else LightAppTypography
    val materialTypography = if (darkTheme) DarkMaterialTypography else LightMaterialTypography
    val shapes = if (darkTheme) DarkAppShapes else LightAppShapes
    val materialShapes = if (darkTheme) DarkMaterialShapes else LightMaterialShapes

    CompositionLocalProvider(
        LocalIsDarkTheme provides darkTheme,
        LocalThemeExtraColors provides extraColors,
        LocalAppTypography provides typography,
        LocalAppShapes provides shapes,
        LocalAppDimensions provides AppDimensionsDefault,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = materialTypography,
            shapes = materialShapes,
            content = content,
        )
    }
}

