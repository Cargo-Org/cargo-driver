package com.example.carog_driver.presentation.screen.onboarding.view

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import carog_driver.composeapp.generated.resources.*
import com.example.carog_driver.presentation.base.ObserveAsEffect
import com.example.carog_driver.presentation.navigation.callbacks.OnboardingNavigationCallbacks
import com.example.carog_driver.presentation.screen.onboarding.view.components.*
import com.example.carog_driver.presentation.screen.onboarding.viewmodel.*
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.theme.CargoTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingScreen(
    navigationCallbacks: OnboardingNavigationCallbacks,
    viewModel: OnboardingViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEffect(viewModel.effect) {
        when (it) {
            OnboardingEffect.NavigateNext -> navigationCallbacks.onNavigateToLogin()
        }
    }

    OnboardingContent(
        data = state.pages,
        interaction = viewModel,
    )
}

@Composable
private fun OnboardingContent(
    data: List<OnboardingPageModel>,
    interaction: OnboardingInteraction,
    modifier: Modifier = Modifier
) {
    val pages = remember { data }

    val pagerState = rememberPagerState {
        pages.size
    }

    val scope = rememberCoroutineScope()

    val isLastPage by remember {
        derivedStateOf {
            pagerState.currentPage == pages.lastIndex
        }
    }

    val buttonLabel = stringResource(
        if (isLastPage) {
            Res.string.get_started
        } else {
            Res.string.next
        }
    )

    Box(
        modifier = modifier.fillMaxSize().background(AppTheme.colors.background)
            .safeContentPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(
                    horizontal = AppTheme.dimens.pageMargin, vertical = AppTheme.dimens.md
                ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(
                onClick = interaction::onSkipClick, modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = stringResource(Res.string.skip),
                    style = AppTheme.typography.labelMd,
                    color = AppTheme.colors.primary
                )
            }

            OnboardingPager(
                pagerState = pagerState,
                pages = pages,
                currentPage = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth().height(560.dp)
            )

            Spacer(modifier = Modifier.height(AppTheme.dimens.stackMd))

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
                                page = pagerState.currentPage + 1, animationSpec = tween(
                                    durationMillis = 600, easing = FastOutSlowInEasing
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
            data = listOf(
                OnboardingPageModel(
                    image = Res.drawable.onboarding_1,
                    title = Res.string.onboarding_title_1,
                    subtitle = Res.string.onboarding_subtitle_1
                ),
            ), interaction = object : OnboardingInteraction {
                override fun onSkipClick() {}

                override fun onFinishClick() {}
            })
    }
}