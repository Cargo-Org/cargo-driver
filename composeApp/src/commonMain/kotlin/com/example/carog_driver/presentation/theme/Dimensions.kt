package com.example.carog_driver.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AppDimensions(
    val base: Dp,
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
    val xxl: Dp,
    val pageMargin: Dp,
    val gutter: Dp,
    val cardPadding: Dp,
    val stackSm: Dp,
    val stackMd: Dp,
    val stackLg: Dp,
)

val AppDimensionsDefault = AppDimensions(
    base = 8.dp,
    xs = 4.dp,
    sm = 12.dp,
    md = 24.dp,
    lg = 40.dp,
    xl = 64.dp,
    xxl = 80.dp,
    pageMargin = 20.dp,
    gutter = 16.dp,
    cardPadding = 24.dp,
    stackSm = 12.dp,
    stackMd = 24.dp,
    stackLg = 40.dp,
)

val LocalAppDimensions = staticCompositionLocalOf { AppDimensionsDefault }

