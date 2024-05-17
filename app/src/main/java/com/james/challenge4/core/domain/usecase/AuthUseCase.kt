package com.james.challenge4.core.domain.usecase

interface AuthUseCase {
    suspend fun register(email : String, password : String)
    suspend fun login (email: String, password: String) : String
    fun loadToken() : String?
    fun clearToken()
    fun saveToken(token: String)
    suspend fun saveName(name:String)
    suspend fun loadName() : String?

}