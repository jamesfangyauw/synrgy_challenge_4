package com.james.challenge4.data.source.local.photo

import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class LocalDataSourcePhoto(private val preferencesDataStore: DataStore<Preferences>) {

    companion object{
        private val KEY_PHOTO = stringPreferencesKey("photo")
    }

    suspend fun savePhoto(photo : String){
        preferencesDataStore.edit { pref ->
            pref[KEY_PHOTO] = photo
        }
    }

    suspend fun loadPhoto() : String? {
        return preferencesDataStore.data.map {pref ->
            pref[KEY_PHOTO]
        }.firstOrNull()

    }


}