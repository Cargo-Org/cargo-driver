package com.example.carog_driver.presentation.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.carog_driver.presentation.theme.AppTheme


@Composable
fun OtpVerificationInput(
    modifier: Modifier = Modifier,
    numberOfDigits: Int = 4,
    onConfirm: (String) -> Unit
) {
    val colors = AppTheme.colors

    val textStates = remember {
        mutableStateListOf<TextFieldValue>().apply {
            repeat(numberOfDigits) {
                add(TextFieldValue(""))
            }
        }
    }
    val focusRequesters = remember {
        List(numberOfDigits) { FocusRequester() }
    }

    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(numberOfDigits) { index ->
            OutlinedTextField(
                modifier = Modifier.weight(1f)
                    .focusRequester(focusRequesters[index]),
                textStyle = AppTheme.typography.bodyStandard.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                ),
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "•",
                        style = AppTheme.typography.bodyStandard.copy(
                            textAlign = TextAlign.Center,
                            color = AppTheme.colors.outline,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                value = textStates[index],
                onValueChange = { value ->
                    val digit = value.text
                        .filter { it.isDigit() }
                        .takeLast(1)

                    textStates[index] = TextFieldValue(
                        text = digit,
                        selection = TextRange(digit.length)
                    )

                    if (digit.isNotEmpty()) {
                        if (index < numberOfDigits - 1) {
                            focusRequesters[index + 1].requestFocus()
                        } else {
                            focusManager.clearFocus()
                            onConfirm(textStates.joinToString("") { it.text })
                        }
                    } else {
                        if (index > 0) {
                            focusRequesters[index - 1].requestFocus()
                        } else {
                            focusManager.clearFocus()
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = colors.surfaceVariant.copy(alpha = 0.3f),
                    focusedContainerColor = colors.surfaceVariant.copy(alpha = 0.3f),
                    unfocusedBorderColor = colors.outline.copy(alpha = 0.4f),
                    focusedBorderColor = colors.primary,
                    cursorColor = Color.Transparent,
                    errorCursorColor = Color.Transparent,
                ),
                visualTransformation = VisualTransformation.None,
            )
        }
    }
}