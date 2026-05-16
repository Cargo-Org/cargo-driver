package com.example.carog_driver.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.carog_driver.presentation.theme.AppTheme

@Composable
fun OnboardingBottomSection(
    pageCount: Int,
    currentPage: Int,
    buttonLabel: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PagerIndicator(
            pageCount = pageCount,
            currentPage = currentPage
        )

        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.stackMd)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            AppTheme.colors.primary,
                            AppTheme.colors.secondary
                        )
                    ),
                    shape = AppTheme.shapes.pill
                ),
            shape = AppTheme.shapes.pill,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = AppTheme.colors.onPrimary
            ),
            contentPadding = PaddingValues(
                vertical = AppTheme.dimens.sm
            )
        ) {
            Text(
                text = buttonLabel,
                style = AppTheme.typography.buttonLabel,
                color = AppTheme.colors.onPrimary
            )
        }
    }
}

@Composable
private fun PagerIndicator(
    pageCount: Int,
    currentPage: Int
) {
    val colors = AppTheme.colors
    val dimens = AppTheme.dimens

    Row(
        horizontalArrangement = Arrangement.spacedBy(dimens.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage

            Box(
                modifier = Modifier
                    .size(
                        width = if (isSelected) dimens.md else dimens.base,
                        height = dimens.base
                    )
                    .clip(CircleShape)
                    .background(
                        color = if (isSelected) {
                            colors.primary
                        } else {
                            colors.outlineVariant
                        }
                    )
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
//    PagerIndicator(
//        pageCount = 3,
//        currentPage = 1
//    )
    OnboardingBottomSection(
        pageCount = 3,
        currentPage = 1,
        buttonLabel = "Next",
        onButtonClick = {},
        modifier = Modifier
    )
}

