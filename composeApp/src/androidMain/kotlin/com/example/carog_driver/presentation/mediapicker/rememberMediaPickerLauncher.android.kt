package com.example.carog_driver.presentation.mediapicker

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.cargo.driver.shared.domain.model.MediaPickedFile
import java.io.File

@Composable
actual fun rememberMediaPickerLauncher(
    onFilePicked: (MediaPickedFile?) -> Unit,
): MediaPickerLauncher {
    val context = LocalContext.current
    var pendingCameraFile by remember { mutableStateOf<File?>(null) }
    var pendingCameraUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            val file = pendingCameraFile
            if (file != null && file.exists()) {
                val bytes = file.readBytes()
                val mimeType = "image/jpeg"
                onFilePicked(
                    MediaPickedFile(
                        name = file.name,
                        sizeBytes = file.length(),
                        mimeType = mimeType,
                        bytes = bytes,
                    )
                )
            }
        } else {
            onFilePicked(null)
        }
        pendingCameraFile?.delete()
        pendingCameraFile = null
        pendingCameraUri = null
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            val (file, uri) = CameraFileProvider.createTempImageUri(context)
            pendingCameraFile = file
            pendingCameraUri = uri
            cameraLauncher.launch(uri)
        } else {
            onFilePicked(null)
        }
    }

    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            val result = uri.toMediaPickedFile(context.contentResolver)
            onFilePicked(result)
        } else {
            onFilePicked(null)
        }
    }

    return remember(cameraLauncher, fileLauncher, permissionLauncher, context) {
        MediaPickerLauncher(
            context = context,
            cameraLauncher = cameraLauncher,
            fileLauncher = fileLauncher,
            permissionLauncher = permissionLauncher,
            onPreLaunchCamera = {
                val permission = Manifest.permission.CAMERA
                val granted = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
                if (granted) {
                    val (file, uri) = CameraFileProvider.createTempImageUri(context)
                    pendingCameraFile = file
                    pendingCameraUri = uri
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(permission)
                }
            }
        )
    }
}

private fun Uri.toMediaPickedFile(resolver: ContentResolver): MediaPickedFile? {
    return try {
        val mimeType = resolver.getType(this) ?: "application/octet-stream"
        var name = "file_${System.currentTimeMillis()}"
        var size = 0L

        resolver.query(this, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
            if (cursor.moveToFirst()) {
                if (nameIndex >= 0) name = cursor.getString(nameIndex)
                if (sizeIndex >= 0) size = cursor.getLong(sizeIndex)
            }
        }

        val bytes = resolver.openInputStream(this)?.use { it.readBytes() } ?: return null

        MediaPickedFile(
            name = name,
            sizeBytes = size.takeIf { it > 0 } ?: bytes.size.toLong(),
            mimeType = mimeType,
            bytes = bytes,
        )
    } catch (e: Exception) {
        null
    }
}