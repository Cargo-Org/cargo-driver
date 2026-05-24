package com.example.carog_driver.presentation.screen.personal.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.*
import com.example.carog_driver.presentation.screen.personal.uimodel.DocumentModel
import com.example.carog_driver.presentation.screen.personal.uimodel.DocumentStatus
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DocumentUploadCard(
    document: DocumentModel,
    modifier: Modifier = Modifier,
    onUploadClick: (DocumentModel) -> Unit = {},
    onViewClick: (DocumentModel) -> Unit = {},
) {
    Card(
        modifier = modifier,
        shape = AppTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surfaceContainerLow
        )
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimens.gutter)
        ) {
            DocumentCardHeader(document = document)

            Spacer(modifier = Modifier.height(AppTheme.dimens.sm))

            when (document.status) {
                DocumentStatus.Pending -> {
                    UploadPlaceholder(
                        onClick = { onUploadClick(document) }
                    )
                }

                DocumentStatus.Uploaded,
                DocumentStatus.InReview,
                DocumentStatus.Verified -> {
                    UploadedDocumentContent(
                        fileName = document.fileName.orEmpty(),
                        onViewClick = { onViewClick(document) }
                    )
                }
            }
        }
    }
}

@Composable
private fun DocumentCardHeader(
    document: DocumentModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        DocumentNumberBadge(number = document.number)

        Spacer(modifier = Modifier.width(AppTheme.dimens.sm))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = document.title,
                color = AppTheme.colors.onSurface,
                style = AppTheme.typography.labelMd
            )

            Text(
                text = document.subTitle,
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall,
                modifier = Modifier.padding(top = AppTheme.dimens.xs)
            )
        }

        DocumentStatusChip(status = document.status)
    }
}

@Composable
private fun DocumentNumberBadge(
    number: Int
) {
    Box(
        modifier = Modifier
            .size(AppTheme.dimens.md + AppTheme.dimens.base)
            .clip(CircleShape)
            .background(AppTheme.extraColors.brandBlue),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            color = AppTheme.colors.onPrimary,
            style = AppTheme.typography.labelMd
        )
    }
}

@Composable
private fun DocumentStatusChip(
    status: DocumentStatus
) {
    val backgroundColor = when (status) {
        DocumentStatus.Pending ->
            AppTheme.extraColors.warningOrangeContainer

        DocumentStatus.Uploaded ->
            AppTheme.extraColors.brandBlue.copy(alpha = 0.16f)

        DocumentStatus.InReview ->
            AppTheme.colors.surfaceContainerHigh

        DocumentStatus.Verified ->
            AppTheme.extraColors.successGreenContainer
    }

    val textColor = when (status) {
        DocumentStatus.Pending ->
            AppTheme.extraColors.warningOrange

        DocumentStatus.Uploaded ->
            AppTheme.extraColors.brandBlue

        DocumentStatus.InReview ->
            AppTheme.colors.onSurfaceVariant

        DocumentStatus.Verified ->
            AppTheme.extraColors.successGreen
    }

    val text = when (status) {
        DocumentStatus.Pending -> stringResource(Res.string.document_status_pending)
        DocumentStatus.Uploaded -> stringResource(Res.string.document_status_uploaded)
        DocumentStatus.InReview -> stringResource(Res.string.document_status_in_review)
        DocumentStatus.Verified -> stringResource(Res.string.document_status_verified)
    }

    Box(
        modifier = Modifier
            .clip(AppTheme.shapes.pill)
            .background(backgroundColor)
            .padding(
                horizontal = AppTheme.dimens.sm,
                vertical = AppTheme.dimens.xs
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = AppTheme.typography.labelSm
        )
    }
}

@Composable
private fun UploadPlaceholder(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(AppTheme.shapes.medium)
            .clickable(onClick = onClick)
            .dashedBorder(
                color = AppTheme.extraColors.divider,
                cornerRadius = AppTheme.dimens.sm
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_camera),
                contentDescription = null,
                tint = AppTheme.extraColors.brandBlue,
                modifier = Modifier.size(AppTheme.dimens.md)
            )

            Text(
                text = stringResource(Res.string.document_upload_action),
                color = AppTheme.extraColors.brandBlue,
                style = AppTheme.typography.labelMd,
                modifier = Modifier.padding(top = AppTheme.dimens.base)
            )

            Text(
                text = stringResource(Res.string.document_upload_hint),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall,
                modifier = Modifier.padding(top = AppTheme.dimens.xs)
            )
        }
    }
}

@Composable
private fun UploadedDocumentContent(
    fileName: String,
    onViewClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.sm)
    ) {
        UploadedFileNameBox(
            fileName = fileName.ifBlank { "license_front_v2.jpg" },
            onClick = onViewClick,
            modifier = Modifier.weight(1f)
        )

        ViewDocumentButton(
            onClick = onViewClick
        )
    }
}

@Composable
private fun UploadedFileNameBox(
    fileName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(AppTheme.dimens.lg + AppTheme.dimens.sm + AppTheme.dimens.xs)
            .clip(AppTheme.shapes.small)
            .background(AppTheme.colors.primaryContainer.copy(alpha = 0.18f))
            .clickable(onClick = onClick)
            .padding(horizontal = AppTheme.dimens.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_file),
            contentDescription = null,
            tint = AppTheme.colors.onSurfaceVariant,
            modifier = Modifier.size(AppTheme.dimens.md)
        )

        Spacer(modifier = Modifier.width(AppTheme.dimens.sm))

        Text(
            text = fileName,
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.bodyMd,
            maxLines = 1
        )
    }
}

@Composable
private fun ViewDocumentButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(AppTheme.dimens.lg + AppTheme.dimens.sm + AppTheme.dimens.xs)
            .clip(AppTheme.shapes.small)
            .background(AppTheme.colors.surfaceContainerLow)
            .border(
                width = 1.dp,
                color = AppTheme.colors.outlineVariant,
                shape = AppTheme.shapes.small
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_eye_on),
            contentDescription = stringResource(Res.string.view_document),
            tint = AppTheme.colors.onSurfaceVariant,
            modifier = Modifier.size(AppTheme.dimens.md)
        )
    }
}

private fun Modifier.dashedBorder(color: Color, cornerRadius: Dp): Modifier {
    return drawBehind {
        drawRoundRect(
            color = color,
            style = Stroke(
                width = 1.3.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(12f, 10f),
                    phase = 0f
                )
            ),
            cornerRadius = CornerRadius(x = cornerRadius.toPx(), y = cornerRadius.toPx())
        )
    }
}

