package com.concordia.canary.authmodule.presentation.state

import com.concordia.canary.authmodule.util.UiText

data class RegisterState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val passwordRepeatedInput: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShow: Boolean = false,
    val isPasswordRepeatedShow: Boolean = false,
    val errorMessageInput: UiText? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyRegisteredIn: Boolean = false,
    val errorMessageLoginProcess: String? = null
)
