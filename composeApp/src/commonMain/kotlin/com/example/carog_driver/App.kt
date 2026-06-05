package com.example.carog_driver

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.carog_driver.presentation.navigation.NavGraph
import com.example.carog_driver.presentation.navigation.Route
import com.example.carog_driver.presentation.theme.CargoTheme

@Composable
@Preview
fun App() {
    CargoTheme {
        NavGraph()
    }
}