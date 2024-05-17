package com.james.challenge_4.domain.usecase

import com.james.challenge_4.domain.repository.IAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInteractor @Inject constructor(private val authRepository: IAuthRepository) : AuthUseCase{
    override suspend fun register(email: String, password: String) {
        authRepository.register(email, password)
    }

    override suspend fun login(email: String, password: String): String {
        return authRepository.login(email, password)
    }

    override fun loadToken(): String? {
        return authRepository.loadToken()
    }

    override fun clearToken() {
        authRepository.clearToken()
    }

    override fun saveToken(token: String) {
        authRepository.saveToken(token)
    }

    override suspend fun saveName(name: String) {
        authRepository.saveName(name)
    }

    override suspend fun loadName(): String? {
        return authRepository.loadName()
    }


}