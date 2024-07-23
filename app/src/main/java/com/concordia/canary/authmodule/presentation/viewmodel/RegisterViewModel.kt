package com.concordia.canary.authmodule.presentation.viewmodel

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel

import com.concordia.canary.authmodule.domain.model.RegisterInputValidationType
import com.concordia.canary.authmodule.domain.repository.AuthRepository
import com.concordia.canary.authmodule.domain.use_case.ValidateRegisterInputUseCase
import com.concordia.canary.authmodule.presentation.state.RegisterState


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase,
    private val authRepository: AuthRepository
) :
    ViewModel() {

    var registerState by mutableStateOf(RegisterState())
        private set;

    fun onEmailInputChange(newValue: String) {
        registerState = registerState.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String) {
        registerState = registerState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onRepeatPasswordInputChange(newValue: String) {
        registerState = registerState.copy(passwordRepeatedInput = newValue)
        checkInputValidation()
    }

    fun onPasswordToggleView() {
        registerState = registerState.copy(isPasswordShow = !registerState.isPasswordShow)
    }

    fun onRegisterClick() {
        registerState = registerState.copy(isLoading = true)

        viewModelScope.launch {
            registerState = try {
                val registerResult =
                    authRepository.login(
                        registerState.emailInput,
                        registerState.passwordInput
                    )
                registerState.copy(isSuccessfullyRegisteredIn = registerResult)
            } catch (e: Exception) {
                registerState.copy(errorMessageLoginProcess = "Could not register")
            } finally {
                registerState = registerState.copy(isLoading = false)
            }
        }
    }

    private fun checkInputValidation() {
        val validateResult = validateRegisterInputUseCase(
            registerState.emailInput,
            registerState.passwordInput,
            registerState.passwordRepeatedInput
        )

        processInputValidationType(validateResult)
    }

    private fun processInputValidationType(result: RegisterInputValidationType) {

        registerState = when (result) {
            RegisterInputValidationType.EmptyField -> {
                registerState.copy(
                    errorMessageInput = "Empty fields left",
                    isInputValid = false
                )
            }

            RegisterInputValidationType.NoEmail -> {
                registerState.copy(
                    errorMessageInput = "No valid email",
                    isInputValid = false
                )
            }

            RegisterInputValidationType.Valid -> {
                registerState.copy(
                    errorMessageInput = null,
                    isInputValid = true
                )
            }

            RegisterInputValidationType.PasswordsDoNotMatch -> {
                registerState.copy(
                    errorMessageInput = "Passwords do not match",
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordUpperCaseMissing -> {
                registerState.copy(
                    errorMessageInput = "password upper case missing",
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordNumberMissing -> {
                registerState.copy(
                    errorMessageInput = "Password missing number",
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordSpecialCharMissing -> {
                registerState.copy(
                    errorMessageInput = "Password missing special character",
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordTooShort -> {
                registerState.copy(
                    errorMessageInput = "Password too short",
                    isInputValid = false
                )
            }
        }
    }
}