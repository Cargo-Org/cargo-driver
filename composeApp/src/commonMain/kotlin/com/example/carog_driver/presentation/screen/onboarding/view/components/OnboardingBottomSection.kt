package com.example.carog_driver.presentation.screen.onboarding.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.carog_driver.presentation.shared.PrimaryButton
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.theme.CargoTheme

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

        PrimaryButton(
            text = buttonLabel,
            onClick = onButtonClick,
            modifier = Modifier.padding(top = AppTheme.dimens.stackMd)
        )
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

@Preview(showBackground = true)
@Composable
private fun OnboardingBottomSectionPreview() {
    CargoTheme {
        OnboardingBottomSection(
            pageCount = 3,
            currentPage = 1,
            buttonLabel = "Next",
            onButtonClick = {}
        )
    }
}