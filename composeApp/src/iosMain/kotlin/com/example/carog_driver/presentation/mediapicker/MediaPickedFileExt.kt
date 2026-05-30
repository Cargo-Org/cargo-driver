package com.example.carog_driver.presentation.mediapicker


import com.cargo.driver.shared.domain.model.MediaPickedFile
import okio.ByteString.Companion.toByteString
import platform.Foundation.NSData
import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

internal fun NSData.toMediaPickedFile(prefix: String): MediaPickedFile {
    val bytes = toByteString().toByteArray()
    return MediaPickedFile(
        name = "${prefix}_${NSDate().timeIntervalSince1970.toLong()}.jpg",
        sizeBytes = bytes.size.toLong(),
        mimeType = "image/jpeg",
        bytes = bytes,
    )
}