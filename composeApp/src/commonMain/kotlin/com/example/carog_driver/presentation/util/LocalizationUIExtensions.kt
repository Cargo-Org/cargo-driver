package com.example.carog_driver.presentation.util

import androidx.compose.ui.text.intl.Locale

fun String.toLocalizedDigits(locale: Locale = Locale.current): String {
    if (locale.language != "ar") return this

    return this.map { char ->
        when (char) {
            '0' -> '٠'
            '1' -> '١'
            '2' -> '٢'
            '3' -> '٣'
            '4' -> '٤'
            '5' -> '٥'
            '6' -> '٦'
            '7' -> '٧'
            '8' -> '٨'
            '9' -> '٩'
            '.' -> '٫'
            else -> char
        }
    }.joinToString("")
}
