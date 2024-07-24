package com.concordia.canary.authmodule.domain.use_case

import org.junit.Test
import com.concordia.canary.authmodule.domain.model.RegisterInputValidationType

class RegisterInputValidationUseCaseTest {

    private val useCase = ValidateRegisterInputUseCase()


    @Test
    fun `test empty field returns empty validate type`() {
        val result = useCase.invoke("", "", "")
        assert(result == RegisterInputValidationType.EmptyField)
    }

}