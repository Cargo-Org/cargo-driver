package com.example.carog_driver.presentation.mediapicker


import com.cargo.driver.shared.domain.model.MediaPickedFile
import platform.PhotosUI.*
import platform.darwin.NSObject

class PHPickerDelegate(
    private val onFilePicked: (MediaPickedFile?) -> Unit,
) : NSObject(), PHPickerViewControllerDelegateProtocol {

    override fun picker(
        picker: PHPickerViewController,
        didFinishPicking: List<*>,
    ) {
        picker.dismissViewControllerAnimated(true, completion = null)

        val result = didFinishPicking.firstOrNull() as? PHPickerResult ?: run {
            onFilePicked(null)
            return
        }

        result.itemProvider.loadDataRepresentationForTypeIdentifier("public.image") { data, _ ->
            onFilePicked(data?.toMediaPickedFile("picked"))
        }
    }
}