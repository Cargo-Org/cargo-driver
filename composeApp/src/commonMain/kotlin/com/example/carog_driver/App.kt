package com.example.carog_driver

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.cargo.driver.shared.domain.usecase.onboarding.GetOnboardingCompletedUseCase
import com.example.carog_driver.presentation.login.view.LoginScreen
import com.example.carog_driver.presentation.onboarding.view.OnboardingScreen
import com.example.carog_driver.presentation.onboarding.viewmodel.OnboardingEffect
import com.example.carog_driver.presentation.onboarding.viewmodel.OnboardingViewModel
import com.example.carog_driver.presentation.theme.CargoTheme
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {

    CargoTheme{

        val getOnboardingCompleted: GetOnboardingCompletedUseCase = koinInject()

        var isOnboardingCompleted by remember {
            mutableStateOf<Boolean?>(null)
        }

        LaunchedEffect(Unit) {
            isOnboardingCompleted = getOnboardingCompleted()
        }

        when (isOnboardingCompleted) {

            false -> {
                val onboardingViewModel: OnboardingViewModel = koinViewModel()

                LaunchedEffect(onboardingViewModel) {
                    onboardingViewModel.effect.collect { effect ->
                        when (effect) {
                            OnboardingEffect.NavigateNext -> {
                                isOnboardingCompleted = true
                            }
                        }
                    }
                }

                OnboardingScreen(
                    viewModel = onboardingViewModel
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
}