package com.example.carog_driver.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carog_driver.presentation.navigation.callbacks.OnboardingNavigationCallbacks
import com.example.carog_driver.presentation.navigation.callbacks.SplashNavigationCallbacks
import com.example.carog_driver.presentation.screen.login.view.LoginScreen
import com.example.carog_driver.presentation.screen.onboarding.view.OnboardingScreen
import com.example.carog_driver.presentation.screen.splash.SplashScreen


@Composable
fun NavGraph(
    modifier: Modifier = Modifier, startDestinationRoute: Route
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = startDestinationRoute, modifier = modifier
    ) {
        composable<Route.SplashRoute> {
            SplashScreen(navigationCallbacks = SplashNavigationCallbacks(onNavigateToOnboarding = {
                navController.navigate(Route.OnboardingRoute)
            }, onNavigateToLogin = {
                navController.navigate(Route.LoginRoute)
            }, onNavigateToHome = {/*TODO navigate to home */ }))
        }

        composable<Route.OnboardingRoute> {
            OnboardingScreen(
                navigationCallbacks = OnboardingNavigationCallbacks(
                onNavigateToLogin = {
                    navController.navigate(Route.LoginRoute)
                }))
        }

        composable<Route.LoginRoute> {
            LoginScreen()
        }
    }
}
