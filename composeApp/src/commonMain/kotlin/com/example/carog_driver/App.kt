package com.example.carog_driver

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cargo.driver.shared.data.local.datastore.TokenStorage
import com.cargo.driver.shared.data.remote.datasource.auth.AuthenticationRemoteDataSource
import com.example.carog_driver.presentation.theme.CargoTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    CargoTheme {

        val authenticationRemoteDataSource: AuthenticationRemoteDataSource = koinInject()
        val tokenStorage: TokenStorage = koinInject()

        var loginResult by remember { mutableStateOf(" ") }

        var profileResult by remember { mutableStateOf(" ") }

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
                            tokenStorage.saveAccessToken(response.accessToken)
                            tokenStorage.saveRefreshToken(response.refreshToken)
                            loginResult = "Welcome ${response.fullName}"

                        } catch (e: Exception) {
                            loginResult = "Error: ${e.message}"
                            println("======= ${e.message}")
                        } finally {
                            isLoading = false
                        }
                    }
                }
            ) {
                Text("login")
            }

            Button(
                onClick = {
                    scope.launch {
                        try {
                            isLoading = true

                            val response = authenticationRemoteDataSource.getUserProfile()

                            profileResult = "Welcome ${response.fullName}"

                        } catch (e: Exception) {
                            profileResult = "Error: ${e.message}"
                            println("======= ${e.message}")
                        } finally {
                            isLoading = false
                        }
                    }
                }
            ) {
                Text("get profile")
            }

            if (isLoading) {
                Text("Loading...")
            }

            Text(text = loginResult)
            Text(text = profileResult)
        }
    }
}