package com.example.carog_driver.presentation.shared.upload

enum class UploadFileStatus {
    Pending,
    Uploaded,
    InReview,
    Verified
}

data class UploadFileUiModel(
    val id: String,
    val number: Int? = null,
    val title: String,
    val subTitle: String,
    val status: UploadFileStatus,
    val fileName: String? = null
)