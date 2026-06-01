package com.example.carog_driver.presentation.mediapicker

import androidx.compose.runtime.Composable
import com.cargo.driver.shared.domain.model.MediaPickedFile

@Composable
expect fun rememberMediaPickerLauncher(
    onFilePicked: (MediaPickedFile?) -> Unit,
): MediaPickerLauncher