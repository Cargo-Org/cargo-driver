package com.example.carog_driver

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.carog_driver.presentation.navigation.NavGraph
import com.example.carog_driver.presentation.navigation.Route
import com.example.carog_driver.presentation.snackbar.CargoSnackbarHost
import com.example.carog_driver.presentation.snackbar.SnackbarManager
import com.example.carog_driver.presentation.theme.CargoTheme

@Composable
fun App() {
    val snackbarHostState = remember { SnackbarHostState() }
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)  {
            SnackbarManager.events.collect { visuals ->
                snackbarHostState.showSnackbar(
                    visuals = visuals
                )
            }
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