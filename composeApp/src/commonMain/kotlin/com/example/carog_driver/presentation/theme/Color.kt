package com.example.carog_driver.presentation.theme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightSurface = Color(0xFFFBF8FF)
private val LightSurfaceDim = Color(0xFFD9D8F0)
private val LightSurfaceBright = Color(0xFFFBF8FF)
private val LightSurfaceContainerLowest = Color(0xFFFFFFFF)
private val LightSurfaceContainerLow = Color(0xFFF5F2FF)
private val LightSurfaceContainer = Color(0xFFEEECFF)
private val LightSurfaceContainerHigh = Color(0xFFE7E6FF)
private val LightSurfaceContainerHighest = Color(0xFFE1E0F9)
private val LightOnSurface = Color(0xFF191A2B)
private val LightOnSurfaceVariant = Color(0xFF474651)
private val LightInverseSurface = Color(0xFF2E2F41)
private val LightInverseOnSurface = Color(0xFFF1EFFF)
private val LightOutline = Color(0xFF777682)
private val LightOutlineVariant = Color(0xFFC8C5D2)
private val LightSurfaceTint = Color(0xFF5855A3)
private val LightPrimary = Color(0xFF05003E)
private val LightOnPrimary = Color(0xFFFFFFFF)
private val LightPrimaryContainer = Color(0xFF1A1464)
private val LightOnPrimaryContainer = Color(0xFF8481D3)
private val LightInversePrimary = Color(0xFFC3C0FF)
private val LightSecondary = Color(0xFF264BDD)
private val LightOnSecondary = Color(0xFFFFFFFF)
private val LightSecondaryContainer = Color(0xFF4666F7)
private val LightOnSecondaryContainer = Color(0xFFFFFBFF)
private val LightTertiary = Color(0xFF150A00)
private val LightOnTertiary = Color(0xFFFFFFFF)
private val LightTertiaryContainer = Color(0xFF331E00)
private val LightOnTertiaryContainer = Color(0xFFBD7C00)
private val LightError = Color(0xFFBA1A1A)
private val LightOnError = Color(0xFFFFFFFF)
private val LightErrorContainer = Color(0xFFFFDAD6)
private val LightOnErrorContainer = Color(0xFF93000A)
private val LightPrimaryFixed = Color(0xFFE2DFFF)
private val LightPrimaryFixedDim = Color(0xFFC3C0FF)
private val LightOnPrimaryFixed = Color(0xFF12095E)
private val LightOnPrimaryFixedVariant = Color(0xFF403D8A)
private val LightSecondaryFixed = Color(0xFFDEE1FF)
private val LightSecondaryFixedDim = Color(0xFFB9C3FF)
private val LightOnSecondaryFixed = Color(0xFF001258)
private val LightOnSecondaryFixedVariant = Color(0xFF0033C2)
private val LightTertiaryFixed = Color(0xFFFFDDB4)
private val LightTertiaryFixedDim = Color(0xFFFFB955)
private val LightOnTertiaryFixed = Color(0xFF291800)
private val LightOnTertiaryFixedVariant = Color(0xFF633F00)
private val LightBackground = Color(0xFFFBF8FF)
private val LightOnBackground = Color(0xFF191A2B)
private val LightSurfaceVariant = Color(0xFFE1E0F9)

private val DarkSurface = Color(0xFF101419)
private val DarkSurfaceDim = Color(0xFF101419)
private val DarkSurfaceBright = Color(0xFF363940)
private val DarkSurfaceContainerLowest = Color(0xFF0B0E14)
private val DarkSurfaceContainerLow = Color(0xFF181C22)
private val DarkSurfaceContainer = Color(0xFF1C2026)
private val DarkSurfaceContainerHigh = Color(0xFF272A30)
private val DarkSurfaceContainerHighest = Color(0xFF31353B)
private val DarkOnSurface = Color(0xFFE0E2EB)
private val DarkOnSurfaceVariant = Color(0xFFC0C7D4)
private val DarkInverseSurface = Color(0xFFE0E2EB)
private val DarkInverseOnSurface = Color(0xFF2D3037)
private val DarkOutline = Color(0xFF8B919E)
private val DarkOutlineVariant = Color(0xFF414752)
private val DarkSurfaceTint = Color(0xFFA4C8FF)
private val DarkPrimary = Color(0xFFA4C8FF)
private val DarkOnPrimary = Color(0xFF00315E)
private val DarkPrimaryContainer = Color(0xFF4B9EFF)
private val DarkOnPrimaryContainer = Color(0xFF003463)
private val DarkInversePrimary = Color(0xFF005FAD)
private val DarkSecondary = Color(0xFFC7C5D5)
private val DarkOnSecondary = Color(0xFF2F2F3C)
private val DarkSecondaryContainer = Color(0xFF464553)
private val DarkOnSecondaryContainer = Color(0xFFB5B3C4)
private val DarkTertiary = Color(0xFFFFB869)
private val DarkOnTertiary = Color(0xFF482900)
private val DarkTertiaryContainer = Color(0xFFDE8800)
private val DarkOnTertiaryContainer = Color(0xFF4D2C00)
private val DarkError = Color(0xFFFFB4AB)
private val DarkOnError = Color(0xFF690005)
private val DarkErrorContainer = Color(0xFF93000A)
private val DarkOnErrorContainer = Color(0xFFFFDAD6)
private val DarkPrimaryFixed = Color(0xFFD4E3FF)
private val DarkPrimaryFixedDim = Color(0xFFA4C8FF)
private val DarkOnPrimaryFixed = Color(0xFF001C3A)
private val DarkOnPrimaryFixedVariant = Color(0xFF004784)
private val DarkSecondaryFixed = Color(0xFFE3E0F2)
private val DarkSecondaryFixedDim = Color(0xFFC7C5D5)
private val DarkOnSecondaryFixed = Color(0xFF1A1A26)
private val DarkOnSecondaryFixedVariant = Color(0xFF464553)
private val DarkTertiaryFixed = Color(0xFFFFDCBB)
private val DarkTertiaryFixedDim = Color(0xFFFFB869)
private val DarkOnTertiaryFixed = Color(0xFF2C1700)
private val DarkOnTertiaryFixedVariant = Color(0xFF683D00)
private val DarkBackground = Color(0xFF101419)
private val DarkOnBackground = Color(0xFFE0E2EB)
private val DarkSurfaceVariant = Color(0xFF31353B)

val LightColorScheme: ColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,
    inversePrimary = LightInversePrimary,
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = LightOnSecondaryContainer,
    tertiary = LightTertiary,
    onTertiary = LightOnTertiary,
    tertiaryContainer = LightTertiaryContainer,
    onTertiaryContainer = LightOnTertiaryContainer,
    error = LightError,
    onError = LightOnError,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightOnSurfaceVariant,
    outline = LightOutline,
    outlineVariant = LightOutlineVariant,
    scrim = Color(0x99000000),
    inverseSurface = LightInverseSurface,
    inverseOnSurface = LightInverseOnSurface,
    surfaceDim = LightSurfaceDim,
    surfaceBright = LightSurfaceBright,
    surfaceContainerLowest = LightSurfaceContainerLowest,
    surfaceContainerLow = LightSurfaceContainerLow,
    surfaceContainer = LightSurfaceContainer,
    surfaceContainerHigh = LightSurfaceContainerHigh,
    surfaceContainerHighest = LightSurfaceContainerHighest,
    surfaceTint = LightSurfaceTint,
)

val DarkColorScheme: ColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,
    inversePrimary = DarkInversePrimary,
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = DarkOnSecondaryContainer,
    tertiary = DarkTertiary,
    onTertiary = DarkOnTertiary,
    tertiaryContainer = DarkTertiaryContainer,
    onTertiaryContainer = DarkOnTertiaryContainer,
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = DarkOnErrorContainer,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,
    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant,
    scrim = Color(0xB3000000),
    inverseSurface = DarkInverseSurface,
    inverseOnSurface = DarkInverseOnSurface,
    surfaceDim = DarkSurfaceDim,
    surfaceBright = DarkSurfaceBright,
    surfaceContainerLowest = DarkSurfaceContainerLowest,
    surfaceContainerLow = DarkSurfaceContainerLow,
    surfaceContainer = DarkSurfaceContainer,
    surfaceContainerHigh = DarkSurfaceContainerHigh,
    surfaceContainerHighest = DarkSurfaceContainerHighest,
    surfaceTint = DarkSurfaceTint,
)

@Immutable
data class ThemeExtraColors(
    val brandBlue: Color,
    val cardShadow: Color,
    val glassOverlay: Color,
    val divider: Color,
)

val LightExtraColors = ThemeExtraColors(
    brandBlue = LightSecondary,
    cardShadow = Color(0x0F000000),
    glassOverlay = Color(0x80FFFFFF),
    divider = LightOutlineVariant,
)

val DarkExtraColors = ThemeExtraColors(
    brandBlue = DarkPrimaryContainer,
    cardShadow = Color(0x33000000),
    glassOverlay = Color(0x1AFFFFFF),
    divider = DarkOutlineVariant,
)

val LocalThemeExtraColors = staticCompositionLocalOf { LightExtraColors }

