package com.example.carog_driver.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val PlusJakartaSans = FontFamily.SansSerif
private val Inter = FontFamily.SansSerif

@Immutable
data class AppTextStyles(
    val displayHero: TextStyle,
    val sectionTitle: TextStyle,
    val headlineXl: TextStyle,
    val headlineLg: TextStyle,
    val bodyStandard: TextStyle,
    val bodyLg: TextStyle,
    val bodyMd: TextStyle,
    val bodySmall: TextStyle,
    val buttonLabel: TextStyle,
    val labelMuted: TextStyle,
    val labelMd: TextStyle,
    val labelSm: TextStyle,
)

private fun lightStyles() = AppTextStyles(
    displayHero = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 32.sp,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 38.sp,
        letterSpacing = (-0.02).sp,
    ),
    sectionTitle = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp,
        letterSpacing = (-0.01).sp,
    ),
    headlineXl = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp,
        letterSpacing = (-0.02).sp,
    ),
    headlineLg = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
    ),
    bodyStandard = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 23.sp,
    ),
    bodyLg = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp,
    ),
    bodyMd = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp,
    ),
    buttonLabel = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp,
        letterSpacing = 0.01.sp,
    ),
    labelMuted = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
    ),
    labelMd = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        letterSpacing = 0.01.sp,
    ),
    labelSm = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        letterSpacing = 0.05.sp,
    ),
)

private fun darkStyles() = AppTextStyles(
    displayHero = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp,
        letterSpacing = (-0.02).sp,
    ),
    sectionTitle = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
    ),
    headlineXl = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp,
        letterSpacing = (-0.02).sp,
    ),
    headlineLg = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
    ),
    bodyStandard = TextStyle(
        fontFamily = Inter,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    bodyLg = TextStyle(
        fontFamily = Inter,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp,
    ),
    bodyMd = TextStyle(
        fontFamily = Inter,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = Inter,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
    ),
    buttonLabel = TextStyle(
        fontFamily = Inter,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        letterSpacing = 0.01.sp,
    ),
    labelMuted = TextStyle(
        fontFamily = Inter,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        letterSpacing = 0.05.sp,
    ),
    labelMd = TextStyle(
        fontFamily = Inter,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        letterSpacing = 0.01.sp,
    ),
    labelSm = TextStyle(
        fontFamily = Inter,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        letterSpacing = 0.05.sp,
    ),
)

val LightAppTypography = lightStyles()
val DarkAppTypography = darkStyles()

val LightMaterialTypography = Typography(
    displayLarge = LightAppTypography.headlineXl,
    headlineLarge = LightAppTypography.headlineLg,
    bodyLarge = LightAppTypography.bodyLg,
    bodyMedium = LightAppTypography.bodyStandard,
    labelLarge = LightAppTypography.labelMd,
    labelSmall = LightAppTypography.labelSm,
)

val DarkMaterialTypography = Typography(
    displayLarge = DarkAppTypography.headlineXl,
    headlineLarge = DarkAppTypography.headlineLg,
    bodyLarge = DarkAppTypography.bodyLg,
    bodyMedium = DarkAppTypography.bodyStandard,
    labelLarge = DarkAppTypography.labelMd,
    labelSmall = DarkAppTypography.labelSm,
)

val LocalAppTypography = staticCompositionLocalOf { LightAppTypography }

