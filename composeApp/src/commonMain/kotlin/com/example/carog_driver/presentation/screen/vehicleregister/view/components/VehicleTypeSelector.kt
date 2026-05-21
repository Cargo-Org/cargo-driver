package com.example.carog_driver.presentation.screen.vehicleregister.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.carog_driver.presentation.theme.AppTheme

data class VehicleTypeOption(
    val id: String,
    val label: String,
    val icon: Painter,
    val isEnabled: Boolean = true,
)

@Composable
fun VehicleTypeSelector(
    options: List<VehicleTypeOption>,
    selectedId: String?,
    onSelect: (VehicleTypeOption) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    minItemWidth: Dp = 100.dp,
    itemHeight: Dp = 100.dp,
) {
    val typography = AppTheme.typography
    val colors = AppTheme.colors
    val dimens = AppTheme.dimens

    Column(modifier = modifier.fillMaxWidth()) {

        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = typography.sectionTitle,
                color = colors.onSurface,
            )
            Spacer(modifier = Modifier.height(dimens.gutter))
        }

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimens.sm),
            verticalArrangement = Arrangement.spacedBy(dimens.sm),
            maxItemsInEachRow = 10,
        ) {
            options.forEach { option ->
                VehicleTypeItem(
                    option = option,
                    isSelected = option.id == selectedId,
                    itemHeight = itemHeight,
                    onSelect = { if (option.isEnabled) onSelect(option) },
                    modifier = Modifier.width(minItemWidth),
                )
            }
        }
    }
}

@Composable
private fun VehicleTypeItem(
    option: VehicleTypeOption,
    isSelected: Boolean,
    itemHeight: Dp,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val dimens = AppTheme.dimens
    val shapes = AppTheme.shapes

    val containerColor = when {
        isSelected && option.isEnabled -> colors.primary
        !option.isEnabled -> colors.surfaceVariant.copy(alpha = 0.3f)
        else -> colors.surfaceContainerLow
    }
    val contentColor = when {
        isSelected && option.isEnabled -> colors.onPrimary
        !option.isEnabled -> colors.onSurfaceVariant.copy(alpha = 0.4f)
        else -> colors.onSurfaceVariant
    }
    val borderColor = when {
        isSelected -> colors.primary
        else -> colors.outline.copy(alpha = 0.25f)
    }

    Box(
        modifier = modifier
            .height(itemHeight)
            .clip(shapes.medium)
            .background(containerColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shapes.medium,
            )
            .clickable(
                enabled = option.isEnabled,
                role = Role.RadioButton,
                onClickLabel = option.label,
                onClick = onSelect,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimens.xs),
            modifier = Modifier.padding(horizontal = dimens.xs),
        ) {
            Icon(
                painter = option.icon,
                contentDescription = option.label,
                tint = contentColor,
                modifier = Modifier.size(36.dp),
            )
            Text(
                text = option.label,
                style = typography.labelMd,
                color = contentColor,
                textAlign = TextAlign.Center,
                maxLines = 2,
            )
        }
    }
}