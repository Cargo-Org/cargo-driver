package com.example.carog_driver

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.example.carog_driver.presentation.navigation.NavGraph
import com.example.carog_driver.presentation.navigation.Route
import com.example.carog_driver.presentation.snackbar.CargoSnackbarHost
import com.example.carog_driver.presentation.snackbar.SnackbarManager
import com.example.carog_driver.presentation.theme.CargoTheme

@Composable
fun App() {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        SnackbarManager.events.collect { visuals ->
            snackbarHostState.showSnackbar(
                visuals = visuals
            )
        }
    }

    CargoTheme {
        Scaffold(
            snackbarHost = {
                CargoSnackbarHost(
                    snackbarHostState = snackbarHostState
                )
            }
        ) { _ ->
            NavGraph(
                startDestinationRoute = Route.LoginRoute //this will change according to start screen
            )
        }
    }
}