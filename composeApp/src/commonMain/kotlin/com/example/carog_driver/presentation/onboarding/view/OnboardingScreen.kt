package com.example.carog_driver.presentation.onboarding.view

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.carog_driver.presentation.onboarding.view.components.OnboardingBottomSection
import com.example.carog_driver.presentation.onboarding.view.components.OnboardingPager
import com.example.carog_driver.presentation.onboarding.model.getOnboardingPages
import com.example.carog_driver.presentation.onboarding.viewmodel.OnboardingEffect
import com.example.carog_driver.presentation.onboarding.viewmodel.OnboardingInteraction
import com.example.carog_driver.presentation.onboarding.viewmodel.OnboardingViewModel
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.theme.CargoTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel,
    navigateNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                OnboardingEffect.NavigateNext -> navigateNext()
            }
        }
    }

    OnboardingContent(
        interaction = viewModel,
        modifier = modifier
    )
}

@Composable
private fun OnboardingContent(
    interaction: OnboardingInteraction,
    modifier: Modifier = Modifier
) {
    val pages = remember {
        getOnboardingPages()
    }

    val pagerState = rememberPagerState {
        pages.size
    }

    val scope = rememberCoroutineScope()

    val isLastPage by remember {
        derivedStateOf {
            pagerState.currentPage == pages.lastIndex
        }
    }

    val buttonLabel = if (isLastPage) {
        "Get Started"
    } else {
        "Next"
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .safeContentPadding()
    ) {
        TextButton(
            onClick = interaction::onSkipClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = AppTheme.dimens.sm,
                    end = AppTheme.dimens.gutter
                )
        ) {
            Text(
                text = "Skip",
                style = AppTheme.typography.labelMd,
                color = AppTheme.colors.primary
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = AppTheme.dimens.pageMargin,
                    vertical = AppTheme.dimens.md
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OnboardingPager(
                pagerState = pagerState,
                pages = pages,
                currentPage = pagerState.currentPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            OnboardingBottomSection(
                pageCount = pages.size,
                currentPage = pagerState.currentPage,
                buttonLabel = buttonLabel,
                onButtonClick = {
                    if (isLastPage) {
                        interaction.onFinishClick()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1,
                                animationSpec = tween(
                                    durationMillis = 600,
                                    easing = FastOutSlowInEasing
                                )
                            )
                        }
                    }
                },
                modifier = Modifier.padding(
                    bottom = AppTheme.dimens.md
                )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF101419, name = "InputField — Dark")
@Composable
private fun InputFieldDarkPreview() {
    CargoTheme(darkTheme = true) {
        OnboardingContent(
            interaction = object : OnboardingInteraction {
                override fun onSkipClick() {}

                override fun onFinishClick() {}
            }
        )
    }
}