package com.example.carog_driver.presentation.mediapicker


import com.cargo.driver.shared.domain.model.MediaPickedFile
import okio.ByteString.Companion.toByteString
import platform.Foundation.NSCalendar
import platform.Foundation.NSData
import platform.Foundation.NSDate
import platform.Foundation.NSDateComponents
import platform.Foundation.timeIntervalSinceDate

internal fun NSData.toMediaPickedFile(prefix: String): MediaPickedFile {
    val calendar = NSCalendar.currentCalendar
    val components = NSDateComponents().apply {
        year = 2003
        month = 4
        day = 27
    }

    val specificDate = calendar.dateFromComponents(components) ?: NSDate()

    val secondsSinceCustomDate = NSDate().timeIntervalSinceDate(specificDate).toLong()

    val bytes = toByteString().toByteArray()

    return MediaPickedFile(
        name = "${prefix}_${secondsSinceCustomDate}.jpg",
        sizeBytes = bytes.size.toLong(),
        mimeType = "image/jpeg",
        bytes = bytes,
    )
}