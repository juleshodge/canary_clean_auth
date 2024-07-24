package com.concordia.canary.authmodule.presentation.viewmodel

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.concordia.canary.authmodule.R
import dagger.hilt.android.lifecycle.HiltViewModel

import com.concordia.canary.authmodule.domain.model.RegisterInputValidationType
import com.concordia.canary.authmodule.domain.repository.AuthRepository
import com.concordia.canary.authmodule.domain.use_case.ValidateRegisterInputUseCase
import com.concordia.canary.authmodule.presentation.state.RegisterState
import com.concordia.canary.authmodule.util.UiText


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
                    errorMessageInput = UiText.StringResource(R.string.register_empty_field_left),
                    isInputValid = false
                )
            }

            RegisterInputValidationType.NoEmail -> {
                registerState.copy(
                    errorMessageInput = UiText.StringResource(R.string.register_no_valid_email_err),
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
                    errorMessageInput = UiText.StringResource(R.string.register_passwords_nomatch_err),
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordUpperCaseMissing -> {
                registerState.copy(
                    errorMessageInput = UiText.StringResource(R.string.register_passwords_upper_case_err),
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordNumberMissing -> {
                registerState.copy(
                    errorMessageInput = UiText.StringResource(R.string.register_password_number_missing),
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordSpecialCharMissing -> {
                registerState.copy(
                    errorMessageInput = UiText.StringResource(R.string.register_password_missing_special_err),
                    isInputValid = false
                )
            }

            RegisterInputValidationType.PasswordTooShort -> {
                registerState.copy(
                    errorMessageInput = UiText.StringResource(R.string.register_password_too_short_err),
                    isInputValid = false
                )
            }
        }
    }
}