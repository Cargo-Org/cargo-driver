package com.example.carog_driver.presentation.mediapicker


import com.cargo.driver.shared.domain.model.MediaPickedFile
import okio.ByteString.Companion.toByteString
import platform.UIKit.*
import platform.PhotosUI.*
import platform.Foundation.*
import platform.darwin.NSObject

class PHPickerDelegate(
    private val viewController: UIViewController,
    private val onFilePicked: (MediaPickedFile?) -> Unit,
) : NSObject(), PHPickerViewControllerDelegateProtocol {

    override fun picker(
        picker: PHPickerViewController,
        didFinishPicking: List<*>,
    ) {
        picker.dismissViewControllerAnimated(true, completion = null)

        val result = didFinishPicking.firstOrNull() as? PHPickerResult
        if (result == null) {
            onFilePicked(null)
            return
        }

        result.itemProvider.loadDataRepresentationForTypeIdentifier(
            typeIdentifier = "public.image"
        ) { data, _ ->
            if (data != null) {
                val bytes = data.toByteString()
                val name = "picked_${NSDate().timeIntervalSince1970.toLong()}.jpg"
                onFilePicked(
                    MediaPickedFile(
                        name = name,
                        sizeBytes = bytes.size.toLong(),
                        mimeType = "image/jpeg",
                        bytes = bytes.toByteArray(),
                    )
                )
            } else {
                onFilePicked(null)
            }
        }
    }
}