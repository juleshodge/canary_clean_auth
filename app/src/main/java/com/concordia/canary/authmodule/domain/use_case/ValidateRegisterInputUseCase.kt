package com.concordia.canary.authmodule.domain.use_case

import com.concordia.canary.authmodule.domain.model.RegisterInputValidationType
import com.concordia.canary.authmodule.util.containsNumber
import com.concordia.canary.authmodule.util.containsSpecialChar
import com.concordia.canary.authmodule.util.containsUpperCase

class ValidateRegisterInputUseCase {

    operator fun invoke(
        email: String,
        password: String,
        passwordRepeated: String
    ): RegisterInputValidationType {

        if (email.isEmpty() || password.isEmpty() || passwordRepeated.isEmpty()) {
            return RegisterInputValidationType.EmptyField
        }

        if ("@" !in email) {
            return RegisterInputValidationType.NoEmail
        }

        if (password != passwordRepeated) {
            return RegisterInputValidationType.PasswordsDoNotMatch
        }

        if (password.count() < 8) {
            return RegisterInputValidationType.PasswordTooShort
        }

        if (!password.containsNumber()) {
            return RegisterInputValidationType.PasswordNumberMissing
        }

        if (!password.containsUpperCase()) {
            return RegisterInputValidationType.PasswordUpperCaseMissing
        }

        if (!password.containsSpecialChar()) {
            return RegisterInputValidationType.PasswordSpecialCharMissing
        }

        return RegisterInputValidationType.Valid

    }
}