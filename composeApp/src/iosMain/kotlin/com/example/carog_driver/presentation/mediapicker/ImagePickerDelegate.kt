package com.example.carog_driver.presentation.mediapicker

import com.cargo.driver.shared.domain.model.MediaPickedFile
import okio.ByteString.Companion.toByteString
import platform.UIKit.*
import platform.Foundation.*
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

        if (image == null) {
            onFilePicked(null)
            return
        }

        val data = UIImageJPEGRepresentation(image, compressionQuality = 0.85)
        if (data == null) {
            onFilePicked(null)
            return
        }

        val bytes = data.toByteString()
        val name = "camera_${NSDate().timeIntervalSince1970.toLong()}.jpg"

        onFilePicked(
            MediaPickedFile(
                name = name,
                sizeBytes = bytes.size.toLong(),
                mimeType = "image/jpeg",
                bytes = bytes.toByteArray(),
            )
        )
    }

    override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
        picker.dismissViewControllerAnimated(true, completion = null)
        onFilePicked(null)
    }
}