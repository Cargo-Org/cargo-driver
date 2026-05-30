package com.example.carog_driver.presentation.mediapicker

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class MediaPickerLauncher(
    private val context: Context,
    private val cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>,
    private val fileLauncher: ManagedActivityResultLauncher<String, Uri?>,
    private val permissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
    private val onPreLaunchCamera: () -> Unit
) {
    actual fun launchCamera() {
        onPreLaunchCamera()
    }

    actual fun launchFilePicker() {
        fileLauncher.launch("*/*")
    }
}