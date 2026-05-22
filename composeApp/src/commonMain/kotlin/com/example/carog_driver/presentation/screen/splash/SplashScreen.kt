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
import carog_driver.composeapp.generated.resources.splash_loading
import carog_driver.composeapp.generated.resources.splash_slogan
import com.example.carog_driver.presentation.shared.AnimatedCargoLogo
import com.example.carog_driver.presentation.shared.AnimatedProgressBar
import com.example.carog_driver.presentation.shared.AnimatedThreeDotsBar
import com.example.carog_driver.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource


@Composable
@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun SplashScreen(modifier: Modifier = Modifier) {
    SplashScreenContent(modifier = modifier)
}

@Composable
private fun SplashScreenContent(modifier: Modifier = Modifier) {

    var logoOffsetX by remember { mutableStateOf(-150f) }

    var cargoOffset by remember { mutableStateOf(50f) }
    var cargoAlpha by remember { mutableStateOf(0f) }

    var sloganOffset by remember { mutableStateOf(50f) }
    var sloganAlpha by remember { mutableStateOf(0f) }

    var progressAlpha by remember { mutableStateOf(0f) }
    var dotsAlpha by remember { mutableStateOf(0f) }
    var progress by remember { mutableStateOf(0f) }
    var dots by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        logoOffsetX = 0f
        delay(700)

        cargoOffset = 0f
        cargoAlpha = 1f
        delay(300)

        sloganOffset = 0f
        sloganAlpha = 1f
        delay(300)

        progressAlpha = 1f
        dotsAlpha     = 1f
        delay(400)

        repeat(5) {
            progress += 0.2f
            dots++
            delay(500)
        }
    }

    val animatedLogoOffsetX by animateFloatAsState(
        targetValue = logoOffsetX,
        animationSpec = tween(700),
        label = "logo offset"
    )

    val cargoTextOffset by animateFloatAsState(
        targetValue = cargoOffset,
        animationSpec = tween(500),
        label = "cargo offset"
    )
    val cargoTextAlpha by animateFloatAsState(
        targetValue = cargoAlpha,
        animationSpec = tween(500),
        label = "slogan offset"
    )

    val sloganTextOffset by animateFloatAsState(
        targetValue = sloganOffset,
        animationSpec = tween(500),
        label = "slogan offset"
    )
    val sloganTextAlpha by animateFloatAsState(
        targetValue = sloganAlpha,
        animationSpec = tween(500),
        label = "slogan offset"
    )

    val progressAlphaAnim by animateFloatAsState(
        targetValue = progressAlpha,
        animationSpec = tween(400),
        label = "progress alpha"
    )

    val dotsAlphaAnim by animateFloatAsState(
        targetValue = dotsAlpha,
        animationSpec = tween(400),
        label = "dots alpha"
    )

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(450),
        label = "progress fill"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        AppTheme.colors.background.copy(alpha = 0.95f),
                        AppTheme.colors.background,
                    )
                )
            )
            .padding(vertical = AppTheme.dimens.lg)
    ) {
        Column(
            modifier = Modifier.matchParentSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    AppTheme.dimens.sm,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = AppTheme.colors.outline,
                            shape = RoundedCornerShape(AppTheme.dimens.md)
                        )
                        .padding(AppTheme.dimens.md)
                        .clipToBounds()
                ) {
                    AnimatedCargoLogo(
                        modifier = Modifier
                            .size(80.dp)
                            .offset(x = animatedLogoOffsetX.dp),
                        truckChassisColor = AppTheme.colors.onBackground,
                        truckWheelsColor  = AppTheme.colors.onBackground
                    )
                }

                Spacer(modifier = Modifier.height(AppTheme.dimens.sm))

                Text(
                    stringResource(Res.string.cargo),
                    modifier = Modifier.offset(y = cargoTextOffset.dp).alpha(cargoTextAlpha),
                    style = AppTheme.typography.displayHero.copy(
                        color       = AppTheme.colors.onBackground,
                        fontWeight  = FontWeight.SemiBold,
                        fontSize    = 34.sp,
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
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(6.dp)
                        .alpha(progressAlphaAnim),
                    progress = animatedProgress
                )

                Text(
                    stringResource(Res.string.splash_loading),
                    modifier = Modifier.alpha(progressAlphaAnim),
                    style = AppTheme.typography.bodyLg.copy(
                        color = AppTheme.colors.onBackground
                    )
                )
            }

            AnimatedThreeDotsBar(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .alpha(dotsAlphaAnim),
                progress = dots
            )
        }
    }
}