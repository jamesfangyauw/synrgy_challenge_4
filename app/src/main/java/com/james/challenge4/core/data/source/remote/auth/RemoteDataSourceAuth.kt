package com.james.challenge4.core.data.source.remote.auth

import android.content.SharedPreferences
import kotlinx.coroutines.delay

class RemoteDataSourceAuth (private val sharedPreferences: SharedPreferences) {

    companion object{
        const val KEY_EMAIL = "email@gmail.com"
        const val KEY_PASSWORD = "rahasia"
    }
    suspend fun register(email : String, password : String) {
        delay(1000)
        sharedPreferences.edit().putString(KEY_EMAIL, email).apply()
        sharedPreferences.edit().putString(KEY_PASSWORD, password).apply()
    }

    suspend fun login(email: String, password: String) : String{
        delay(1000)
        if (email == sharedPreferences.getString(KEY_EMAIL,null) && password == sharedPreferences.getString(
                KEY_PASSWORD, null)){
            return "initoken123"
        } else {
            throw UnsupportedOperationException("email atau password anda salah")
        }
    }
}