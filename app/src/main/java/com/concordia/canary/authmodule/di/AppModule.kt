package com.concordia.canary.authmodule.di

import com.concordia.canary.authmodule.data.AuthRepositoryImpl
import com.concordia.canary.authmodule.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import com.concordia.canary.authmodule.domain.use_case.ValidateLoginInputUseCase
import com.concordia.canary.authmodule.domain.use_case.ValidateRegisterInputUseCase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCase()
    }

    @Singleton
    @Provides
    fun provideValidateRegisterInputUseCase(): ValidateRegisterInputUseCase {
        return ValidateRegisterInputUseCase()
    }

    @Singleton
    @Provides
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }
}