package com.example.carog_driver.presentation.onboarding

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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.carog_driver.presentation.onboarding.components.OnboardingBottomSection
import com.example.carog_driver.presentation.onboarding.components.OnboardingPager
import com.example.carog_driver.presentation.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit = {}
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
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .safeContentPadding()
    ) {
        TextButton(
            onClick = onFinish,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = AppTheme.dimens.sm,
                    end = AppTheme.dimens.gutter
                )
        ) {
            Text(
                text = "Skip",
                style =  AppTheme.typography.labelMd,
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
                    scope.launch {
                        if (isLastPage) {
                            onFinish()
                        } else {
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
                modifier = Modifier.padding(bottom = AppTheme.dimens.md)
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    OnboardingScreen(
        onFinish = {println("finish")}
    )
}