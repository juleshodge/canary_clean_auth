package com.concordia.canary.authmodule.domain.use_case

import com.concordia.canary.authmodule.domain.model.LoginInputValidationType
import org.junit.Test

class ValidateLoginUseCaseTest {

    private val validateLoginInputUseCase = ValidateLoginInputUseCase()

    @Test
    fun `test empty field returns empty validate type`() {
        val result = validateLoginInputUseCase.invoke("", "")
        assert(result == LoginInputValidationType.EmptyField)
    }

    @Test
    fun `test email invalid returns invalid email type`() {
        val result = validateLoginInputUseCase.invoke("asdf", "asdf")
        assert(result == LoginInputValidationType.NoEmail)
    }

    @Test
    fun `test all correct values returns login val type valid `() {
        val result = validateLoginInputUseCase.invoke("asdf@mail.com", "asdf")
        assert(result == LoginInputValidationType.Valid)
    }

}