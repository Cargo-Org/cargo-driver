package com.example.carog_driver.presentation.mediapicker

import com.cargo.driver.shared.domain.model.MediaPickedFile
import platform.PhotosUI.PHPickerConfiguration
import platform.PhotosUI.PHPickerFilter
import platform.PhotosUI.PHPickerViewController
import platform.UIKit.UIImagePickerController
import platform.UIKit.UIImagePickerControllerSourceType
import platform.UIKit.UIViewController

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class MediaPickerLauncher(
    private val viewController: UIViewController,
    private val onFilePicked: (MediaPickedFile?) -> Unit,
) {
    actual fun launchCamera() {
        if (!UIImagePickerController.isSourceTypeAvailable(UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera)) {
            onFilePicked(null)
            return
        }

        val picker = UIImagePickerController().apply {
            sourceType = UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera
            mediaTypes = listOf("public.image")
            delegate = ImagePickerDelegate(onFilePicked)
        }
        viewController.presentViewController(picker, animated = true, completion = null)
    }

    actual fun launchFilePicker() {
        val config = PHPickerConfiguration().apply {
            filter = PHPickerFilter.imagesFilter
            selectionLimit = 1
        }
        val picker = PHPickerViewController(configuration = config).apply {
            delegate = PHPickerDelegate(onFilePicked)
        }
        viewController.presentViewController(picker, animated = true, completion = null)
    }
}