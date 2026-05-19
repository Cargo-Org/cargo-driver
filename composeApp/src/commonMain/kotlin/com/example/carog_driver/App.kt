package com.example.carog_driver

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import carog_driver.composeapp.generated.resources.Res
import carog_driver.composeapp.generated.resources.compose_multiplatform
import com.example.carog_driver.presentation.navigation.NavGraph
import com.example.carog_driver.presentation.theme.AppTheme
import com.example.carog_driver.presentation.theme.CargoTheme

@Composable
@Preview
fun App() {
    CargoTheme{
        NavGraph()
    }
}