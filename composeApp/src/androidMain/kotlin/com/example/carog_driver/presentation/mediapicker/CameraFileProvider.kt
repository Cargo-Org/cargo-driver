package com.example.carog_driver.presentation.mediapicker

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

object CameraFileProvider {
    fun createTempImageUri(context: Context): Pair<File, Uri> {
        val cameraDir = File(context.cacheDir, "camera").also { it.mkdirs() }
        val tempFile = File.createTempFile(
            "camera_${System.currentTimeMillis()}",
            ".jpg",
            cameraDir
        )
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            tempFile
        )
        return Pair(tempFile, uri)
    }
}