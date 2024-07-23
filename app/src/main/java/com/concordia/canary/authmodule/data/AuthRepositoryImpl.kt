package com.concordia.canary.authmodule.data

import com.concordia.canary.authmodule.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(email: String, password: String): Boolean {
        delay(1000L)
        return true;
    }

    override suspend fun register(email: String, password: String): Boolean {
        delay(1000L)
        return true;
    }
}