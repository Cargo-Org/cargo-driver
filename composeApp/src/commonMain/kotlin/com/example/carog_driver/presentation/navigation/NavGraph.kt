package com.example.carog_driver.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carog_driver.presentation.screen.login.view.LoginScreen
import com.example.carog_driver.presentation.screen.vehicleregister.view.VehicleRegistrationScreen


@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    startDestinationRoute : Route
){
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestinationRoute,
            modifier = modifier
        ) {
            composable<Route.LoginRoute> {
                VehicleRegistrationScreen()
            }
        }
    }
}
val LocalNavController = compositionLocalOf<NavController> {
    error("NavController not provided")
}