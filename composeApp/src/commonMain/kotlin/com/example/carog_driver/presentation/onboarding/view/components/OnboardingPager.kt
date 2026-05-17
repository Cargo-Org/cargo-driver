package com.example.carog_driver.presentation.onboarding.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.carog_driver.presentation.onboarding.model.OnboardingPageUiState

@Composable
fun OnboardingPager(
    pagerState: PagerState,
    pages: List<OnboardingPageUiState>,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        key = { index ->
            index
        }
    ) { pageIndex ->
        OnboardingPage(
            item = pages[pageIndex],
            currentPage = currentPage,
            pageIndex = pageIndex,
            modifier = Modifier.fillMaxSize()
        )
    }
}