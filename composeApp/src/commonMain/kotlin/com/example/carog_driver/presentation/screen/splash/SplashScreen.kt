package com.example.carog_driver.presentation.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.cargo
import carog_driver.composeapp.generated.resources.splash_slogan
import com.example.carog_driver.presentation.base.ObserveAsEffect
import com.example.carog_driver.presentation.navigation.callbacks.SplashNavigationCallbacks
import com.example.carog_driver.presentation.screen.splash.viewmodel.SplashEffect
import com.example.carog_driver.presentation.screen.splash.viewmodel.SplashViewModel
import com.example.carog_driver.presentation.shared.AnimatedCargoLogo
import com.example.carog_driver.presentation.shared.AnimatedProgressBar
import com.example.carog_driver.presentation.shared.AnimatedThreeDotsBar
import com.example.carog_driver.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

const val SPLASH_DURATION_MS = 3700L

@Composable
@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun SplashScreen(
    navigationCallbacks: SplashNavigationCallbacks, viewModel: SplashViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        delay(SPLASH_DURATION_MS)
        viewModel.determineNextDestination()
    }

    ObserveAsEffect(viewModel.effect) { effects ->
        when (effects) {
            SplashEffect.NavigateToOnboarding -> navigationCallbacks.onNavigateToOnboarding()
            SplashEffect.NavigateToLogin -> navigationCallbacks.onNavigateToLogin()
            SplashEffect.NavigateToHome -> navigationCallbacks.onNavigateToHome()
        }
    }

    SplashScreenContent()
}


@Composable
private fun SplashScreenContent() {

    var logoOffsetX by remember { mutableStateOf(-150f) }

    var cargoOffset by remember { mutableStateOf(50f) }
    var cargoAlpha by remember { mutableStateOf(0f) }

    var sloganOffset by remember { mutableStateOf(50f) }
    var sloganAlpha by remember { mutableStateOf(0f) }

    var progressAlpha by remember { mutableStateOf(0f) }
    var dotsAlpha by remember { mutableStateOf(0f) }
    var progress by remember { mutableStateOf(0f) }
    var dots by remember { mutableIntStateOf(0) }

    val logoSlideMs = 700
    val textSlideMs = 500
    val fadeInMs = 400
    val dotsFadeMs = 300
    val dotStepMs = 300L
    val progressFillMs = (dotStepMs * 6).toInt()

    LaunchedEffect(Unit) {
        logoOffsetX = 0f
        delay(logoSlideMs.toLong())

        cargoOffset = 0f
        cargoAlpha = 1f
        delay(textSlideMs.toLong())

        sloganOffset = 0f
        sloganAlpha = 1f
        delay(textSlideMs.toLong())

        progressAlpha = 1f
        dotsAlpha = 1f
        progress = 1f

        ++dots; delay(dotStepMs)
        ++dots; delay(dotStepMs)
        ++dots; delay(dotStepMs)
        ++dots; delay(dotStepMs)
        ++dots
    }

    val animatedLogoOffsetX by animateFloatAsState(
        targetValue = logoOffsetX, animationSpec = tween(logoSlideMs), label = "logo offset"
    )

    val cargoTextOffset by animateFloatAsState(
        targetValue = cargoOffset, animationSpec = tween(textSlideMs), label = "cargo offset"
    )
    val cargoTextAlpha by animateFloatAsState(
        targetValue = cargoAlpha, animationSpec = tween(textSlideMs), label = "cargo alpha"
    )

    val sloganTextOffset by animateFloatAsState(
        targetValue = sloganOffset, animationSpec = tween(textSlideMs), label = "slogan offset"
    )
    val sloganTextAlpha by animateFloatAsState(
        targetValue = sloganAlpha, animationSpec = tween(textSlideMs), label = "slogan alpha"
    )

    val progressAlphaAnim by animateFloatAsState(
        targetValue = progressAlpha, animationSpec = tween(fadeInMs), label = "progress alpha"
    )

    val dotsAlphaAnim by animateFloatAsState(
        targetValue = dotsAlpha, animationSpec = tween(dotsFadeMs), label = "dots alpha"
    )

    val animatedProgress by animateFloatAsState(
        targetValue = progress, animationSpec = tween(progressFillMs), label = "progress fill"
    )

    Box(
        modifier = Modifier.fillMaxSize().background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        AppTheme.colors.background.copy(alpha = 0.95f),
                        AppTheme.colors.background,
                    )
                )
            ).padding(vertical = AppTheme.dimens.xl)
    ) {
        Column(
            modifier = Modifier.matchParentSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(
                    AppTheme.dimens.sm, Alignment.CenterVertically
                ), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.border(
                            width = 2.dp,
                            color = AppTheme.colors.outline,
                            shape = RoundedCornerShape(AppTheme.dimens.md)
                        ).padding(AppTheme.dimens.md).clipToBounds()
                ) {
                    AnimatedCargoLogo(
                        modifier = Modifier.size(AppTheme.dimens.xxl)
                            .offset(x = animatedLogoOffsetX.dp),
                    )
                }

                Spacer(modifier = Modifier.height(AppTheme.dimens.sm))

                Text(
                    stringResource(Res.string.cargo),
                    modifier = Modifier.offset(y = cargoTextOffset.dp).alpha(cargoTextAlpha),
                    style = AppTheme.typography.titleLg.copy(
                        color = AppTheme.colors.onBackground,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 3.sp
                    )
                )

                Text(
                    stringResource(Res.string.splash_slogan),
                    modifier = Modifier.offset(y = sloganTextOffset.dp).alpha(sloganTextAlpha),
                    style = AppTheme.typography.labelMuted.copy(
                        color = AppTheme.colors.outline
                    )
                )

                Spacer(modifier = Modifier.height(AppTheme.dimens.md))

                AnimatedProgressBar(
                    modifier = Modifier.fillMaxWidth(0.7f).height(AppTheme.dimens.xs + 2.dp)
                        .alpha(progressAlphaAnim), progress = animatedProgress
                )
            }

            AnimatedThreeDotsBar(
                modifier = Modifier.fillMaxWidth(0.5f).alpha(dotsAlphaAnim), progress = dots
            )
        }
    }
}