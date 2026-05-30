package com.example.carog_driver.presentation.mediapicker

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class MediaPickerLauncher(
    private val onLaunchCamera: () -> Unit,
    private val onLaunchFilePicker: () -> Unit,
) {
    actual fun launchCamera() = onLaunchCamera()
    actual fun launchFilePicker() = onLaunchFilePicker()
}