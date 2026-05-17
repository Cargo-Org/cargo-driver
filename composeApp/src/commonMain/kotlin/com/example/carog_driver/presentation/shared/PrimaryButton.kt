package com.example.carog_driver.presentation.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.theme.CargoTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(50.dp),
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val dimens = AppTheme.dimens

    Button(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.primary,
            contentColor = colors.onPrimary,
            disabledContainerColor = colors.primary.copy(alpha = 0.4f),
            disabledContentColor = colors.onPrimary.copy(alpha = 0.6f),
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(dimens.lg + dimens.xs),
    ) {
        Text(
            text = text,
            style = typography.buttonLabel,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF101419, name = "PrimaryButton — Dark")
@Composable
private fun PrimaryButtonDarkPreview() {
    CargoTheme(darkTheme = true) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            PrimaryButton(
                text = "Sign In",
                onClick = {},
            )
        }
    }
}