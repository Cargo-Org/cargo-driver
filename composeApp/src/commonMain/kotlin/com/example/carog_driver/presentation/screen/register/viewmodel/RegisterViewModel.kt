package com.example.carog_driver.presentation.screen.register.viewmodel

import com.cargo.driver.shared.domain.model.register.RegisterRequest
import com.example.carog_driver.presentation.base.BaseViewModel

class RegisterViewModel(

): BaseViewModel<RegisterUiState, RegisterEffect>(), RegisterInteraction {

    override fun register(registerRequest: RegisterRequest) {
        TODO("Not yet implemented")
    }

    override fun registerWithGoogle() {
        TODO("Not yet implemented")
    }

    override fun navigateToLoginScreen() {
        TODO("Not yet implemented")
    }

}