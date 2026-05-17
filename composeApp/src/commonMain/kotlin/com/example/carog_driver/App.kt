package com.example.carog_driver

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.carog_driver.presentation.onboarding.view.OnboardingScreen
import com.example.carog_driver.presentation.onboarding.viewmodel.OnboardingViewModel
import com.cargo.driver.shared.di.SharedDependencies


@Composable
fun App(
    sharedDependencies: SharedDependencies
) {

    var isOnboardingCompleted by remember {
        mutableStateOf<Boolean?>(null)
    }

    LaunchedEffect(sharedDependencies) {
        sharedDependencies
            .observeOnboardingCompleted()
            .collect { isCompleted ->
                isOnboardingCompleted = isCompleted
            }
    }

    when (isOnboardingCompleted) {


        false -> {
            val onboardingViewModel = remember(sharedDependencies) {
                OnboardingViewModel(
                    sharedDependencies.observeOnboardingCompleted,
                    sharedDependencies.completeOnboarding
                )
            }

            OnboardingScreen(
                viewModel = onboardingViewModel,
                navigateNext = {

                }
            )
        }

        true -> {
            Text(text = "Login or Home Screen")
        }
        null -> {
            Text(text = "Loading...")
        }
    }
}
