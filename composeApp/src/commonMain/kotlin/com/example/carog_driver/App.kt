package com.example.carog_driver

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cargo.driver.shared.data.remote.datasource.auth.AuthenticationRemoteDataSource
import com.example.carog_driver.presentation.theme.CargoTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    CargoTheme {
        /*NavGraph(
            startDestinationRoute = Route.LoginRoute //this will change according to start screen
        )*/

            val authenticationRemoteDataSource: AuthenticationRemoteDataSource = koinInject()

            var result by remember { mutableStateOf("Click to test API") }
            var isLoading by remember { mutableStateOf(false) }

            val scope = rememberCoroutineScope()

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            try {
                                isLoading = true

                                val response = authenticationRemoteDataSource.login(
                                    email = "zazaoskar928@gmail.com",
                                    password = "zz123123"
                                )

                                result = "Welcome ${response.fullName}"

                            } catch (e: Exception) {
                                result = "Error: ${e.message}"
                                println("======= ${e.message}")
                            } finally {
                                isLoading = false
                            }
                        }
                    }
                ) {
                    Text("Test API")
                }

                if (isLoading) {
                    Text("Loading...")
                }

                Text(text = result)
            }
        }
    }