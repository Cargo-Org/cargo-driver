package com.example.carog_driver.presentation.shared.upload

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.cancel
import carog_driver.composeapp.generated.resources.ic_file
import com.example.carog_driver.presentation.shared.PrimaryButton
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FilePreviewDialog(
    fileName: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = AppTheme.shapes.large,
            colors = CardDefaults.cardColors(containerColor = AppTheme.colors.surface),
            modifier = Modifier.fillMaxWidth().padding(AppTheme.dimens.pageMargin)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(AppTheme.dimens.stackMd),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = fileName,
                    style = AppTheme.typography.sectionTitle,
                    color = AppTheme.colors.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(AppTheme.dimens.stackMd))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(AppTheme.colors.surfaceContainerHigh, AppTheme.shapes.medium)
                        .clip(AppTheme.shapes.medium),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_file),
                        contentDescription = null,
                        tint = AppTheme.colors.onSurfaceVariant,
                        modifier = Modifier.size(64.dp)
                    )
                }

                Spacer(modifier = Modifier.height(AppTheme.dimens.stackMd))

                PrimaryButton(
                    text = stringResource(Res.string.cancel),
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}