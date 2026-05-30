package com.example.carog_driver.presentation.screen.register.viewmodel

sealed interface RegisterUiState

sealed class LoadingState: RegisterUiState

sealed class ErrorState(
    val message: String
): RegisterUiState

sealed class SuccessState(
    val userId: String
): RegisterUiState