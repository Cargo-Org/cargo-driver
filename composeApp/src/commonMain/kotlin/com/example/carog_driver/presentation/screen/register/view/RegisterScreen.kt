package com.example.carog_driver.presentation.screen.register.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.carog_driver.presentation.screen.register.view.components.RegisterFormContent
import com.example.carog_driver.presentation.screen.register.view.components.RegisterLoading
import com.example.carog_driver.presentation.screen.register.viewmodel.RegisterEffect
import com.example.carog_driver.presentation.screen.register.viewmodel.RegisterInteraction
import com.example.carog_driver.presentation.screen.register.viewmodel.RegisterUiState
import com.example.carog_driver.presentation.screen.register.viewmodel.RegisterViewModel
import com.example.carog_driver.presentation.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                RegisterEffect.NavigateToLoginScreen -> {
                    //TODO: Navigate to Login screen
                    println("NavigateToLoginScreen")
                }

                RegisterEffect.NavigateToOtpScreen -> {
                    //TODO: Navigate to Otp screen
                    println("NavigateToOtpScreen")
                }
            }
        }
    }

    RegisterScreenContent(
        interaction = viewModel,
        state = state
    )
}


@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    interaction: RegisterInteraction,
    state: RegisterUiState,
) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(AppTheme.colors.background),
    ) {

        RegisterFormContent(
            interaction = interaction,
            state = state
        )

        if (state.isLoading) {
            RegisterLoading()
        }
    }
}