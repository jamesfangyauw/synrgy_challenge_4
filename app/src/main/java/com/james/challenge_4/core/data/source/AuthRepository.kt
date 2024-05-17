package com.james.challenge_4.core.data.source

import com.james.challenge_4.core.data.source.local.auth.LocalDataSourceAuth
import com.james.challenge_4.core.data.source.remote.RemoteDataSourceAuth
import com.james.challenge_4.domain.repository.IAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val remoteDataSourceAuth: RemoteDataSourceAuth,
    private val localDataSourceAuth: LocalDataSourceAuth
) : IAuthRepository{
    override suspend fun register(email: String, password: String) {
        remoteDataSourceAuth.register(email,password)
    }

    override suspend fun login(email: String, password: String): String {
        return remoteDataSourceAuth.login(email, password)
    }

    override fun saveToken(token: String) {
        localDataSourceAuth.saveToken(token)
    }


    override fun loadToken(): String? {
        return localDataSourceAuth.loadToken()
    }

    override fun clearToken() {
        localDataSourceAuth.clearToken()
    }

    override suspend fun saveName(name: String) {
        localDataSourceAuth.saveName(name)
    }

    override suspend fun loadName(): String? {
        return localDataSourceAuth.loadName()
    }
}