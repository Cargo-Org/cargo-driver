package com.example.carog_driver.presentation.mediapicker

import com.cargo.driver.shared.domain.model.MediaPickedFile
import platform.UIKit.*
import platform.darwin.NSObject

class ImagePickerDelegate(
    private val onFilePicked: (MediaPickedFile?) -> Unit,
) : NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {

    override fun imagePickerController(
        picker: UIImagePickerController,
        didFinishPickingMediaWithInfo: Map<Any?, *>,
    ) {
        picker.dismissViewControllerAnimated(true, completion = null)

        val image = didFinishPickingMediaWithInfo[UIImagePickerControllerEditedImage] as? UIImage
            ?: didFinishPickingMediaWithInfo[UIImagePickerControllerOriginalImage] as? UIImage

        val data = image?.let { UIImageJPEGRepresentation(it, compressionQuality = 0.85) }
        onFilePicked(data?.toMediaPickedFile("camera"))
    }

    override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
        picker.dismissViewControllerAnimated(true, completion = null)
        onFilePicked(null)
    }
}