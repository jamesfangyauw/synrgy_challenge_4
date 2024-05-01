package com.james.challenge_4.core.data.source.local.auth

import android.content.SharedPreferences

class LocalDataSourceAuth (private val sharedPreferences: SharedPreferences) {

    companion object{
        const val KEY_TOKEN = "token"
    }

    fun saveToken(token : String){
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    fun loadToken(): String?{
        return sharedPreferences.getString(KEY_TOKEN,null)
    }
    fun clearToken(){
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }
}