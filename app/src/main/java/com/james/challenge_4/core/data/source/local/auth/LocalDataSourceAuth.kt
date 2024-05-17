package com.james.challenge_4.core.data.source.local.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


class LocalDataSourceAuth (private val sharedPreferences: SharedPreferences, private val preferencesDataStore: DataStore<Preferences>) {

    companion object{
        const val KEY_TOKEN = "token"
        private val KEY_NAME = stringPreferencesKey("name")
    }

    fun saveToken(token : String){
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }
    suspend fun saveName(name : String){
        preferencesDataStore.edit { preferences ->
            preferences[KEY_NAME] = name
        }
    }

    suspend fun loadName() : String? {
       return preferencesDataStore.data.map { preferences ->
            preferences[KEY_NAME] ?: "user"
        }.firstOrNull()
    }

    fun loadToken(): String?{
        return sharedPreferences.getString(KEY_TOKEN,null)
    }
    fun clearToken(){
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }
}