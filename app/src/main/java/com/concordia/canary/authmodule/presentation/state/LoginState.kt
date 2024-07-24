package com.concordia.canary.authmodule.presentation.state

import com.concordia.canary.authmodule.util.UiText

data class LoginState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShow: Boolean = false,
    val errorMessageInput: UiText? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyLoggedIn: Boolean = false,
    val errorMessageLoginProcess: String? = null
)
