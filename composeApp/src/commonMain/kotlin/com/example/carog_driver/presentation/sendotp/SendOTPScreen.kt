package com.example.carog_driver.presentation.sendotp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.didnt_receive_code
import carog_driver.composeapp.generated.resources.ic_privacy
import carog_driver.composeapp.generated.resources.otp_sent_message
import carog_driver.composeapp.generated.resources.resend_available_in
import carog_driver.composeapp.generated.resources.resend_code
import carog_driver.composeapp.generated.resources.verify_otp
import carog_driver.composeapp.generated.resources.verify_your_id
import com.example.carog_driver.presentation.shared.OTPVerificationInput
import com.example.carog_driver.presentation.shared.PrimaryButton
import com.example.carog_driver.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun SendOTPScreen(
    modifier: Modifier = Modifier
){
    SendOTPContent(
        modifier = modifier
    )
}


@Composable
private fun SendOTPContent(
    modifier: Modifier = Modifier
) {

    val resendPenalties = listOf(
        1 * 60L,
        5 * 60L,
        60 * 60L,
        12 * 60 * 60L,
        24 * 60 * 60L
    )

    var resendCount by remember { mutableIntStateOf(0) }
    var remainingTime by remember { mutableLongStateOf(0L) }
    var isResendEnabled by remember { mutableStateOf(true) }

    fun startResendTimer() {
        val index = minOf(resendCount, resendPenalties.lastIndex)
        remainingTime = resendPenalties[index]
        isResendEnabled = false
    }

    LaunchedEffect(Unit) {
        startResendTimer()
    }

    LaunchedEffect(remainingTime) {
        while (remainingTime > 0) {
            delay(1000)
            remainingTime--
        }
        isResendEnabled = true
    }

    fun formatTime(seconds: Long): String {
        val h = seconds / 3600
        val m = (seconds % 3600) / 60
        val s = seconds % 60

        return when {
            h > 0 -> "${h}h ${m}m ${s}s"
            m > 0 -> "${m}m ${s}s"
            else -> "${s}s"
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
            .background(AppTheme.colors.surfaceVariant)
            .padding(horizontal = AppTheme.dimens.md)
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxHeight(fraction = 0.7f)
                    .fillMaxWidth().align(Alignment.Center)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(AppTheme.dimens.md))
                    .border(
                        shape = RoundedCornerShape(AppTheme.dimens.md),
                        width = 0.dp,
                        color = AppTheme.colors.background
                    )
                    .background(AppTheme.colors.background)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(all = AppTheme.dimens.md).align(
                    Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.lg),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.md),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .border(
                                color = AppTheme.colors.outline,
                                width = 2.dp,
                                shape = RoundedCornerShape(AppTheme.dimens.sm)
                            )
                            .background(
                                shape = RoundedCornerShape(AppTheme.dimens.sm),
                                color = AppTheme.colors.onPrimaryContainer
                            )
                            .padding(all = AppTheme.dimens.sm)
                    ) {
                        Icon(
                            modifier = Modifier.size(AppTheme.dimens.lg),
                            painter = painterResource(Res.drawable.ic_privacy),
                            contentDescription = null,
                            tint = AppTheme.colors.onBackground
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.sm),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(Res.string.verify_your_id),
                            style = AppTheme.typography.sectionTitle.copy(
                                color = AppTheme.colors.onBackground
                            )
                        )

                        Text(
                            text = stringResource(Res.string.otp_sent_message),
                            textAlign = TextAlign.Center,
                            style = AppTheme.typography.bodyStandard.copy(
                                color = AppTheme.colors.onBackground
                            )
                        )
                    }
                }

                OTPVerificationInput(
                    onConfirm = { otp ->

                    }
                )

                PrimaryButton(
                    text = stringResource(Res.string.verify_otp),
                    onClick = {
                        //TODO: Verify OTP
                    }
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(Res.string.didnt_receive_code),
                        style = AppTheme.typography.labelMd.copy(
                            color = AppTheme.colors.outline
                        )
                    )
                    TextButton(
                        onClick = {
                            if (isResendEnabled) {
                                resendCount++
                                startResendTimer()
                            }
                        },
                        enabled = isResendEnabled,
                    ){
                        Text(
                            text = if (isResendEnabled) {
                                stringResource(Res.string.resend_code)
                            } else {
                                stringResource(Res.string.resend_available_in, formatTime(remainingTime))
                            },
                            style = AppTheme.typography.labelMd.copy(
                                color =  if (isResendEnabled) {
                                    AppTheme.colors.primary
                                } else {
                                    AppTheme.colors.outline
                                }
                            )
                        )
                    }
                }
            }
        }
    }
}
