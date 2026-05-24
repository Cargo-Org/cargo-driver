package com.example.carog_driver.presentation.screen.vehicleregister.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import carog_driver.composeapp.generated.resources.*
import com.example.carog_driver.presentation.shared.PrimaryButton
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.screen.vehicleregister.view.components.GeneralInfoSection
import com.example.carog_driver.presentation.screen.vehicleregister.view.components.PageHeader
import com.example.carog_driver.presentation.screen.vehicleregister.view.components.RegistrationSection
import com.example.carog_driver.presentation.screen.vehicleregister.view.components.VehicleTypeOption
import com.example.carog_driver.presentation.screen.vehicleregister.view.components.VehicleTypeSelector
import com.example.carog_driver.presentation.shared.upload.FileUploadCard
import com.example.carog_driver.presentation.shared.upload.UploadFileStatus
import com.example.carog_driver.presentation.shared.upload.UploadFileUiModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun VehicleRegistrationScreen() {
    VehicleRegistrationContent()
}

@Composable
private fun VehicleRegistrationContent() {

    val colors = AppTheme.colors
    val dimens = AppTheme.dimens

    var selectedVehicleId by remember { mutableStateOf<String?>(null) }

    val vehicleOptions = listOf(
        VehicleTypeOption(
            id = "motorcycle",
            label = stringResource(Res.string.vehicle_type_motorcycle),
            icon = painterResource(Res.drawable.ic_motorcycle),
        ),
        VehicleTypeOption(
            id = "car",
            label = stringResource(Res.string.vehicle_type_car),
            icon = painterResource(Res.drawable.ic_car),
        ),
        VehicleTypeOption(
            id = "van",
            label = stringResource(Res.string.vehicle_type_van),
            icon = painterResource(Res.drawable.ic_van),
        ),
        VehicleTypeOption(
            id = "box_truck",
            label = stringResource(Res.string.vehicle_type_box_truck),
            icon = painterResource(Res.drawable.ic_box_truck),
        ),
        VehicleTypeOption(
            id = "heavy_truck",
            label = stringResource(Res.string.vehicle_type_heavy_duty),
            icon = painterResource(Res.drawable.ic_heavy_truck),
        ),
    )

    val documentFile = UploadFileUiModel(
        id = "doc_1",
        number = 1,
        title = stringResource(Res.string.doc_label),
        subTitle = stringResource(Res.string.doc_hint),
        status = UploadFileStatus.Pending
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
    ) {

        PageHeader(
            title = stringResource(Res.string.vehicle_registration_title),
            subtitle = stringResource(Res.string.vehicle_registration_subtitle),
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(
                    horizontal = dimens.pageMargin,
                    vertical = dimens.stackMd,
                ),
        )

        RegistrationSection(
            title = stringResource(Res.string.section_vehicle_type),
            modifier = Modifier.padding(horizontal = dimens.pageMargin),
        ) {
            VehicleTypeSelector(
                options = vehicleOptions,
                selectedId = selectedVehicleId,
                onSelect = { selectedVehicleId = it.id },
            )
        }

        Spacer(modifier = Modifier.height(dimens.stackLg))

        RegistrationSection(
            title = stringResource(Res.string.section_general_info),
            modifier = Modifier.padding(horizontal = dimens.pageMargin),
        ) {
            GeneralInfoSection()
        }

        Spacer(modifier = Modifier.height(dimens.stackLg))

        RegistrationSection(
            title = stringResource(Res.string.section_documentation),
            modifier = Modifier.padding(horizontal = dimens.pageMargin),
        ) {
            FileUploadCard(
                file = documentFile,
                onUploadClick = { },
                onViewClick = { },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(dimens.stackMd))

        PrimaryButton(
            text = stringResource(Res.string.btn_complete_registration),
            onClick = { println("Selected Vehicle ID: $selectedVehicleId") },
            enabled = selectedVehicleId != null,
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = dimens.pageMargin),
        )

        Spacer(modifier = Modifier.height(dimens.stackMd))
    }
}