package com.james.challenge4.core.domain.repository

interface IAuthRepository {
    suspend fun register(email : String, password : String)
    suspend fun login (email: String, password: String) : String
    fun saveToken(token:String)
    fun loadToken() : String?
    fun clearToken()
    suspend fun saveName(name:String)
    suspend fun loadName() : String?
}