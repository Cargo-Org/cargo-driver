package com.example.carog_driver.presentation.screen.register.viewmodel

sealed interface RegisterUiState{
    object LoadingState: RegisterUiState

    data class RegisterErrorState(
        val message: String
    ): RegisterUiState

    data class SuccessState(
        val userId: String
    ): RegisterUiState
}