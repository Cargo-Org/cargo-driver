package com.example.carog_driver.presentation.screen.register.viewmodel

sealed interface RegisterUiState{
    object LoadingState: RegisterUiState
    object InitialState: RegisterUiState

    data class RegisterErrorState(
        val message: String
    ): RegisterUiState

    object SuccessState: RegisterUiState
}