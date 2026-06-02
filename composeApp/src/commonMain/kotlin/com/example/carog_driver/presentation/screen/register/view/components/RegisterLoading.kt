package com.example.carog_driver.presentation.screen.register.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.carog_driver.presentation.shared.AnimatedCargoLogo
import com.example.carog_driver.presentation.theme.AppTheme


@Composable
fun RegisterLoading(
    modifier: Modifier = Modifier
) {
    val colors = AppTheme.colors

    Box(
        modifier = modifier.fillMaxSize()
            .background(color = colors.background.copy(alpha = 0.7f))
    ) {
        Column(
            modifier = Modifier.matchParentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AnimatedCargoLogo(modifier = Modifier.size(AppTheme.dimens.xxl + AppTheme.dimens.md))

        }
    }
}