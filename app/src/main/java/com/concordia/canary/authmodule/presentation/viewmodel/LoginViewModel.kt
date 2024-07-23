package com.concordia.canary.authmodule.presentation.viewmodel

import javax.inject.Inject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel

import com.concordia.canary.authmodule.domain.use_case.ValidateLoginInputUseCase
import com.concordia.canary.authmodule.presentation.state.LoginState
import com.concordia.canary.authmodule.domain.model.LoginInputValidationType
import com.concordia.canary.authmodule.domain.repository.AuthRepository
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {
    var loginState by mutableStateOf(LoginState())
        private set;

    fun onEmailInputChange(newValue: String) {
        loginState = loginState.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String) {
        loginState = loginState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onPasswordToggleView() {
        loginState = loginState.copy(isPasswordShow = !loginState.isPasswordShow)
    }

    fun onLoginClick() {
        loginState = loginState.copy(isLoading = true)

        viewModelScope.launch {
            loginState = try {
                val loginResult =
                    authRepository.login(
                        loginState.emailInput,
                        loginState.passwordInput
                    )
                loginState.copy(isSuccessfullyLoggedIn = loginResult)
            } catch (e: Exception) {
                loginState.copy(errorMessageLoginProcess = "Could not login")
            } finally {
                loginState = loginState.copy(isLoading = false)
            }
        }
    }

    private fun checkInputValidation() {
        val validateResult = validateLoginInputUseCase(
            loginState.emailInput,
            loginState.passwordInput
        )

        processInputValidationType(validateResult)
    }

    private fun processInputValidationType(result: LoginInputValidationType) {
        loginState = when (result) {
            LoginInputValidationType.EmptyField -> {
                loginState.copy(
                    errorMessageInput = "Empty fields left",
                    isInputValid = false
                )
            }

            LoginInputValidationType.NoEmail -> {
                loginState.copy(
                    errorMessageInput = "No valid email",
                    isInputValid = false
                )
            }

            LoginInputValidationType.Valid -> {
                loginState.copy(
                    errorMessageInput = null,
                    isInputValid = true
                )
            }
        }
    }
}