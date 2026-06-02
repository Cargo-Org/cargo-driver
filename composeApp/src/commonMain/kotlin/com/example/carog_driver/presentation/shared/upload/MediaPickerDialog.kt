package com.example.carog_driver.presentation.shared.upload

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.ic_camera
import carog_driver.composeapp.generated.resources.ic_file
import carog_driver.composeapp.generated.resources.cancel
import carog_driver.composeapp.generated.resources.media_source_sheet_title
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaSourcePickerSheet(
    onCameraSelected: () -> Unit,
    onFileSelected: () -> Unit,
    onDismiss: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
) {
    val colors = AppTheme.colors

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = colors.surface,
        dragHandle = {}
    ) {
        MediaSourceSheetContent(
            onCameraSelected = onCameraSelected,
            onFileSelected = onFileSelected,
            onDismiss = onDismiss
        )
    }
}

@Composable
private fun MediaSourceSheetContent(
    onCameraSelected: () -> Unit,
    onFileSelected: () -> Unit,
    onDismiss: () -> Unit,
) {
    val colors = AppTheme.colors
    val dimens = AppTheme.dimens
    val typography = AppTheme.typography
    val shapes = AppTheme.shapes

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.media_source_sheet_title),
            style = typography.sectionTitle,
            color = colors.primary,
            modifier = Modifier.padding(vertical = dimens.stackMd)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MediaSourceGridItem(
                icon = {
                    Surface(
                        shape = shapes.pill,
                        color = AppTheme.extraColors.brandBlue,
                        modifier = Modifier.size(dimens.xl)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_camera),
                                contentDescription = null,
                                tint = colors.onPrimary,
                                modifier = Modifier.size(dimens.md),
                            )
                        }
                    }
                },
                onClick = {
                    onCameraSelected()
                    onDismiss()
                }
            )

            MediaSourceGridItem(
                icon = {
                    Surface(
                        shape = shapes.pill,
                        color = AppTheme.extraColors.brandBlue,
                        modifier = Modifier.size(dimens.xl)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_file),
                                contentDescription = null,
                                tint = colors.onPrimary,
                                modifier = Modifier.size(dimens.md),
                            )
                        }
                    }
                },
                onClick = {
                    onFileSelected()
                    onDismiss()
                }
            )
        }

        Spacer(modifier = Modifier.height(dimens.stackLg))

        Button(
            onClick = onDismiss,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimens.pageMargin)
                .height(dimens.lg),
            shape = shapes.pill,
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primaryContainer,
                contentColor = colors.onPrimaryContainer
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(
                text =  stringResource(Res.string.cancel),
                style = typography.labelMd,
                color = colors.onPrimary
            )
        }

        Spacer(modifier = Modifier.height(dimens.stackMd))
    }
}

@Composable
private fun MediaSourceGridItem(
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
) {
   
    val dimens = AppTheme.dimens
    val shapes = AppTheme.shapes

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(shapes.large)
            .clickable(role = Role.Button, onClick = onClick)
            .focusable(false)
            .padding(dimens.sm)
    ) {
        icon()
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMediaSourcePickerSheet() {
    CargoTheme {
        MediaSourceSheetContent(
            onCameraSelected = {},
            onFileSelected = {},
            onDismiss = {}
        )
    }
}