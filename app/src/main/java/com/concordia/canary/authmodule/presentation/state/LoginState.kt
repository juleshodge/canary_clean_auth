package com.concordia.canary.authmodule.presentation.state

data class LoginState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShow: Boolean = false,
    val errorMessageInput: String? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyLoggedIn: Boolean = false,
    val errorMessageLoginProcess: String? = null
)
