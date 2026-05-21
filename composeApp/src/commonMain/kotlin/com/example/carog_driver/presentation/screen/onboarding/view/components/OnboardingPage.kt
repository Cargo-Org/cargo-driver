package com.example.carog_driver.presentation.screen.onboarding.view.components

import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.carog_driver.presentation.screen.onboarding.viewmodel.OnboardingPageModel
import com.example.carog_driver.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingPage(
    item: OnboardingPageModel,
    currentPage: Int,
    pageIndex: Int,
    modifier: Modifier = Modifier
){
    var visible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(currentPage){
        visible = false

        if(currentPage == pageIndex){
            delay(100)
            visible = true
        }
    }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween (
            durationMillis = 700
        ),
    )

    val offsetY by animateFloatAsState(
        targetValue = if (visible) 0f else 50f,
        animationSpec = tween(
            durationMillis = 700,
            easing = EaseOutCubic
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer {
                this.alpha = alpha
                translationY = offsetY
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(item.image),
                contentDescription = null,
                modifier = Modifier.size(320.dp)
            )
        }


        Spacer(modifier = Modifier.height(AppTheme.dimens.stackLg))

        Text(
            text = stringResource(item.title),
            color = AppTheme.colors.onBackground,
            style = AppTheme.typography.displayHero,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppTheme.dimens.stackSm))

        Text(
            text = stringResource(item.subtitle),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.bodyMd,
            textAlign = TextAlign.Center
        )
    }
}