package com.example.carog_driver.presentation.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.ic_google
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun SocialButton(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(50.dp),
    iconTint: Color = Color.Unspecified
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val dimens = AppTheme.dimens

    OutlinedButton(
        onClick = onClick,
        shape = shape,
        border = BorderStroke(1.dp, colors.outline.copy(alpha = 0.4f)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = colors.surface,
            contentColor = colors.onSurface,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(dimens.lg),
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(20.dp),
        )
        Spacer(modifier = Modifier.width(dimens.sm - dimens.xs))
        Text(
            text = text,
            style = typography.labelMd,
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF101419, name = "PrimaryButton — Dark")
@Composable
private fun PrimaryButtonDarkPreview() {
    CargoTheme(darkTheme = false) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            SocialButton(
                text = "Sign In",
                icon = painterResource(Res.drawable.ic_google),
                onClick = {}
            )
        }
    }
}
