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
import com.cargo.driver.shared.domain.exception.NoInternetException
import com.cargo.driver.shared.domain.exception.ServerException
import com.cargo.driver.shared.domain.exception.UnauthorizedException
import com.cargo.driver.shared.domain.result.ApiResult
import com.example.carog_driver.presentation.theme.CargoTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    CargoTheme {

        val authDataSource: AuthenticationRemoteDataSource = koinInject()

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
                        isLoading = true

                        when (val result = authDataSource.login(
                            email = "zazaoskar928@gmail.com",
                            password = "zz123123"
                        )) {
                            is ApiResult.Success -> {
                                loginResult = "Welcome ${result.data.fullName}"
                            }
                            is ApiResult.Error -> {
                                loginResult = when (result.exception) {
                                    is UnauthorizedException -> "Wrong email or password"
                                    is NoInternetException -> "No internet connection"
                                    is ServerException -> "Server error, try again later"
                                    else -> "Something went wrong"
                                }
                            }
                        }

                        isLoading = false
                    }
                }
            ) {
                Text("Login")
            }

            Button(
                onClick = {
                    scope.launch {
                        isLoading = true

                        when (val result = authDataSource.getUserProfile()) {
                            is ApiResult.Success -> {
                                profileResult = "Welcome ${result.data.fullName}"
                            }
                            is ApiResult.Error -> {
                                profileResult = when (result.exception) {
                                    is UnauthorizedException -> "Session expired, please log in"
                                    is NoInternetException -> "No internet connection"
                                    is ServerException -> "Server error, try again later"
                                    else -> "Something went wrong"
                                }
                            }
                        }

                        isLoading = false
                    }
                }
            ) {
                Text("Get Profile")
            }

            if (isLoading) Text("Loading...")
            Text(text = loginResult)
            Text(text = profileResult)
        }
    }
}