package com.example.carog_driver.presentation.screen.personal.uimodel
enum class DocumentStatus{
    Pending,
    Uploaded,
    InReview,
    Verified
}
data class DocumentModel(
    val number: Int,
    val title: String,
    val subTitle: String,
    val status: DocumentStatus,
    val fileName: String? = null
)