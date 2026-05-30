package com.example.carog_driver.presentation.mediapicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.uikit.LocalUIViewController
import com.cargo.driver.shared.domain.model.MediaPickedFile

@Composable
actual fun rememberMediaPickerLauncher(
    onFilePicked: (MediaPickedFile?) -> Unit,
): MediaPickerLauncher {
    val uiViewController = LocalUIViewController.current
    val currentOnFilePicked by rememberUpdatedState(onFilePicked)

    return remember {
        MediaPickerLauncher(
            viewController = uiViewController,
            onFilePicked = { file -> currentOnFilePicked(file) },
        )
    }
}