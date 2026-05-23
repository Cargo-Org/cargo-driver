package com.example.carog_driver.presentation.screen.personal.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import carog_driver.composeapp.generated.resources.*
import com.example.carog_driver.presentation.screen.personal.view.components.DocumentModel
import com.example.carog_driver.presentation.screen.personal.view.components.DocumentStatus
import com.example.carog_driver.presentation.screen.personal.view.components.DocumentUploadCard
import com.example.carog_driver.presentation.screen.personal.view.components.DocumentsProgressCard
import com.example.carog_driver.presentation.screen.personal.view.components.SecureInfoCard
import com.example.carog_driver.presentation.shared.PrimaryButton
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun DriverDocumentsScreen() {
    DriverDocumentsContent()
}

@Composable
private fun DriverDocumentsContent() {
    val idFrontTitle = stringResource(Res.string.document_id_front_title)
    val idFrontSubtitle = stringResource(Res.string.document_id_front_subtitle)

    val idBackTitle = stringResource(Res.string.document_id_back_title)
    val idBackSubtitle = stringResource(Res.string.document_id_back_subtitle)

    val criminalRecordTitle = stringResource(Res.string.document_criminal_record_title)
    val criminalRecordSubtitle = stringResource(Res.string.document_criminal_record_subtitle)

    val drugTestTitle = stringResource(Res.string.document_drug_test_title)
    val drugTestSubtitle = stringResource(Res.string.document_drug_test_subtitle)

    val documents = remember(
        idFrontTitle,
        idFrontSubtitle,
        idBackTitle,
        idBackSubtitle,
        criminalRecordTitle,
        criminalRecordSubtitle,
        drugTestTitle,
        drugTestSubtitle
    ) {
        mutableStateListOf(
            DocumentModel(
                number = 1,
                title = idFrontTitle,
                subTitle = idFrontSubtitle,
                status = DocumentStatus.Pending
            ),
            DocumentModel(
                number = 2,
                title = idBackTitle,
                subTitle = idBackSubtitle,
                status = DocumentStatus.Pending
            ),
            DocumentModel(
                number = 3,
                title = criminalRecordTitle,
                subTitle = criminalRecordSubtitle,
                status = DocumentStatus.Pending
            ),
            DocumentModel(
                number = 4,
                title = drugTestTitle,
                subTitle = drugTestSubtitle,
                status = DocumentStatus.Pending
            )
        )
    }

    var isSubmitted by remember { mutableStateOf(false) }

    val completedCount = documents.count { document ->
        document.status == DocumentStatus.Uploaded ||
                document.status == DocumentStatus.InReview ||
                document.status == DocumentStatus.Verified
    }

    val totalCount = documents.size
    val allDocumentsUploaded = completedCount == totalCount
    val canSubmit = allDocumentsUploaded && !isSubmitted

    fun markDocumentAsUploaded(document: DocumentModel) {
        val index = documents.indexOfFirst { it.number == document.number }

        if (index != -1) {
            documents[index] = document.copy(
                status = DocumentStatus.Uploaded,
                uploadedAt = "May 22, 2026"
            )

            isSubmitted = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = AppTheme.dimens.pageMargin)
                .padding(
                    top = AppTheme.dimens.md,
                    bottom = AppTheme.dimens.md
                )
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gutter)
        ) {
            DocumentsHeader()

            AnimatedVisibility(
                visible = !isSubmitted
            ) {
                DocumentsProgressCard(
                    completedCount = completedCount,
                    totalCount = totalCount
                )
            }

            documents.forEach { document ->
                DocumentUploadCard(
                    modifier = Modifier.fillMaxWidth(),
                    document = document,
                    onUploadClick = { selectedDocument ->
                        markDocumentAsUploaded(selectedDocument)
                    },
                    onViewClick = {

                    }
                )
            }

            SecureInfoCard()

            Spacer(modifier = Modifier.padding(top = AppTheme.dimens.xs))

            PrimaryButton(
                text = when {
                    isSubmitted -> stringResource(Res.string.documents_button_submitted)
                    allDocumentsUploaded -> stringResource(Res.string.documents_button_submit)
                    else -> stringResource(Res.string.documents_button_complete_required)
                },
                enabled = canSubmit,
                onClick = {
                    documents.indices.forEach { index ->
                        val document = documents[index]

                        if (document.status == DocumentStatus.Uploaded) {
                            documents[index] = document.copy(
                                status = DocumentStatus.InReview
                            )
                        }
                    }

                    isSubmitted = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AppTheme.dimens.stackSm)
            )
        }
    }
}

@Composable
private fun DocumentsHeader() {
    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.xs)
    ) {
        Text(
            text = stringResource(Res.string.compliance_documents_title),
            color = AppTheme.colors.onBackground,
            style = AppTheme.typography.headlineLg
        )

        Text(
            text = stringResource(Res.string.compliance_documents_subtitle),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
private fun DriverDocumentsScreenPreview() {
    CargoTheme(
        darkTheme = true
    ) {
        DriverDocumentsScreen()
    }
}