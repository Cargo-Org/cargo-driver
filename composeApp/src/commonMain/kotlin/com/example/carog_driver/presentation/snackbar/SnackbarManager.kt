package com.example.carog_driver.presentation.snackbar

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object SnackbarManager {
    private val _events = Channel<CargoSnackbarConfig>()
    val events = _events.receiveAsFlow()

    suspend fun show(config: CargoSnackbarConfig) {
        _events.send(config)
    }
}