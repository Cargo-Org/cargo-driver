package com.example.carog_driver.presentation.snackbar

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object SnackbarManager {
    private val _events = MutableSharedFlow<CargoSnackbarConfig>()
    val events = _events.asSharedFlow()

    suspend fun show(config: CargoSnackbarConfig) {
        _events.emit(config)
    }
}