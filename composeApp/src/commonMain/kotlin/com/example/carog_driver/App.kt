package com.example.carog_driver

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.cargo.driver.shared.domain.usecase.onboarding.ObserveOnboardingCompletedUseCase
import com.example.carog_driver.presentation.login.view.LoginScreen
import com.example.carog_driver.presentation.onboarding.view.OnboardingScreen
import org.koin.compose.koinInject

@Composable
fun App() {

    val observeOnboardingCompleted: ObserveOnboardingCompletedUseCase =
        koinInject()

    var isOnboardingCompleted by remember {
        mutableStateOf<Boolean?>(null)
    }

    LaunchedEffect(observeOnboardingCompleted) {
        observeOnboardingCompleted()
            .collect { isCompleted ->
                isOnboardingCompleted = isCompleted
            }
    }

    when (isOnboardingCompleted) {
        false -> {
            OnboardingScreen(
                navigateNext = {
                    isOnboardingCompleted = true
                }
            )
        }

        true -> {
            LoginScreen()
        }

        null -> {
            Text(text = "Loading...")
        }
    }
}