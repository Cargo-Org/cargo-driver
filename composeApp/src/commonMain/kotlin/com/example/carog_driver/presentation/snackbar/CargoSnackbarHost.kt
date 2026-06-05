package com.example.carog_driver.presentation.snackbar

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable


@Composable
fun CargoSnackbarHost(
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(hostState = snackbarHostState) { snackbarData ->
        CargoSnackbar(
            snackbarData = snackbarData
        )
    }
}