package com.example.carog_driver.presentation.shared

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import carog_driver.composeapp.generated.resources.*
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

data class UploadedFile(
    val id: String,
    val name: String,
    val sizeLabel: String,
    val mimeType: String,
)

@Composable
fun FileUploadField(
    uploadedFiles: List<UploadedFile>,
    onPickFile: () -> Unit,
    onRemoveFile: (UploadedFile) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    hint: String = "",
    dropZoneHeight: Dp = 90.dp,
    allowMultiple: Boolean = true,
    uploadIcon: Painter? = null,
    deleteIcon: Painter? = null,
    fileIcon: Painter? = null,
    enabled: Boolean = true,
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val dimens = AppTheme.dimens


    val resolvedHint = hint.ifEmpty { stringResource(Res.string.doc_hint) }

    Column(modifier = modifier.fillMaxWidth()) {

        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = typography.labelMuted,
                color = colors.onSurfaceVariant,
                modifier = Modifier.padding(bottom = dimens.xs),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dropZoneHeight)
                .clip(RoundedCornerShape(12.dp))
                .background(colors.surfaceVariant.copy(alpha = 0.15f))
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (enabled) colors.outline.copy(alpha = 0.35f)
                        else colors.outline.copy(alpha = 0.15f),
                    ),
                    shape = RoundedCornerShape(12.dp),
                )
                .clickable(
                    enabled = enabled,
                    role = Role.Button,
                    onClickLabel = stringResource(Res.string.action_pick_file),
                    onClick = onPickFile,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimens.xs),
            ) {
                if (uploadIcon != null) {
                    Icon(
                        painter = uploadIcon,
                        contentDescription = null,
                        tint = colors.onSurfaceVariant.copy(alpha = 0.6f),
                        modifier = Modifier.size(28.dp),
                    )
                }
                Text(
                    text = resolvedHint,
                    style = typography.labelSm,
                    color = colors.onSurfaceVariant.copy(alpha = 0.55f),
                )
            }
        }

        if (uploadedFiles.isNotEmpty()) {
            Spacer(modifier = Modifier.height(dimens.xs))
            Column(verticalArrangement = Arrangement.spacedBy(dimens.xs)) {
                uploadedFiles.forEach { file ->
                    UploadedFileRow(
                        file = file,
                        fileIcon = fileIcon,
                        deleteIcon = deleteIcon,
                        onRemove = { onRemoveFile(file) },
                    )
                }
            }
        }
    }
}

@Composable
private fun UploadedFileRow(
    file: UploadedFile,
    fileIcon: Painter?,
    deleteIcon: Painter?,
    onRemove: () -> Unit,
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val dimens = AppTheme.dimens

    val removeLabel = stringResource(Res.string.action_remove_file, file.name)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(colors.surfaceContainerLow)
            .padding(horizontal = dimens.gutter, vertical = dimens.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (fileIcon != null) {
            Icon(
                painter = fileIcon,
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(dimens.sm))
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = file.name,
                style = typography.labelMd,
                color = colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = file.sizeLabel,
                style = typography.labelSm,
                color = colors.onSurfaceVariant.copy(alpha = 0.6f),
            )
        }

        if (deleteIcon != null) {
            Spacer(modifier = Modifier.width(dimens.sm))
            Icon(
                painter = deleteIcon,
                contentDescription = removeLabel,
                tint = colors.onSurfaceVariant,
                modifier = Modifier
                    .size(18.dp)
                    .clickable(
                        role = Role.Button,
                        onClickLabel = removeLabel,
                        onClick = onRemove,
                    ),
            )
        }
    }
}