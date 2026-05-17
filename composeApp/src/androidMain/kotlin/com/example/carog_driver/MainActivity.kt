package com.example.carog_driver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cargo.driver.shared.di.createSharedDependencies


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val sharedDependencies = createSharedDependencies(
            context = this
        )

        setContent {
            App(
                sharedDependencies = sharedDependencies
            )
        }
    }
}

