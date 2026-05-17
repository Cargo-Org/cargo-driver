package com.example.carog_driver.presentation.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.theme.CargoTheme

@Composable
fun TextLink(
    prefixText: String,
    linkText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = prefixText,
            style = typography.bodySmall,
            color = colors.onSurfaceVariant.copy(alpha = 0.7f),
        )
        TextButton(
            onClick = onClick,
            contentPadding = PaddingValues(horizontal = 2.dp, vertical = 0.dp),
        ) {
            Text(
                text = linkText,
                style = typography.labelMuted,
                color = colors.primary,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF101419, name = "TextLink — Dark")
@Composable
private fun TextLinkDarkPreview() {
    CargoTheme(darkTheme = true) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextLink(
                prefixText = "Don't have an account?",
                linkText = "Sign up",
                onClick = {},
            )
        }
    }
}