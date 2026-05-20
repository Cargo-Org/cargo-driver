package com.example.carog_driver.presentation.vehicleregister.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import carog_driver.composeapp.generated.resources.*
import com.example.carog_driver.presentation.shared.InputField
import com.example.carog_driver.presentation.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun GeneralInfoSection() {
    val dimens = AppTheme.dimens

    var makeModel by rememberSaveable { mutableStateOf("") }
    var color by rememberSaveable { mutableStateOf("") }
    var year by rememberSaveable { mutableStateOf("") }
    var licensePlate by rememberSaveable { mutableStateOf("") }
    var vin by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(dimens.gutter)
    ) {

        InputField(
            label = stringResource(Res.string.field_make_model),
            value = makeModel,
            onValueChange = { makeModel = it },
            placeholder = stringResource(Res.string.field_make_model_placeholder),
        )

        InputField(
            label = stringResource(Res.string.field_color),
            value = color,
            onValueChange = { color = it },
            placeholder = stringResource(Res.string.field_color_placeholder),
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(dimens.sm),
            modifier = Modifier.fillMaxWidth(),
        ) {

            InputField(
                label = stringResource(Res.string.field_year),
                value = year,
                onValueChange = { year = it },
                placeholder = stringResource(Res.string.field_year_placeholder),
                keyboardType = KeyboardType.Number,
                modifier = Modifier.weight(1f),
            )

            InputField(
                label = stringResource(Res.string.field_license_plate),
                value = licensePlate,
                onValueChange = { licensePlate = it },
                placeholder = stringResource(Res.string.field_license_plate_placeholder),
                modifier = Modifier.weight(1f),
            )
        }

        InputField(
            label = stringResource(Res.string.field_vin),
            value = vin,
            onValueChange = { vin = it },
            placeholder = stringResource(Res.string.field_vin_placeholder),
            keyboardType = KeyboardType.Ascii,
        )
    }
}