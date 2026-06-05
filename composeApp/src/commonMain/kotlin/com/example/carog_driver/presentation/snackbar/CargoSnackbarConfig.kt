package com.example.carog_driver.presentation.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals


data class CargoSnackbarConfig(
    override val message: String,
    val type: SnackbarType,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    val withUndoAction: Boolean = false,
    val onUndo: () -> Unit = {},
) : SnackbarVisuals {
    override val actionLabel = null
    override val withDismissAction: Boolean = false
}