package com.james.challenge4.di.auth

import android.content.Context
import com.james.challenge4.data.SharedPreferencesFactory
import com.james.challenge4.data.source.local.auth.LocalDataSourceAuth
import com.james.challenge4.data.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceAuthModul {

    @Provides
    fun provideLocalDataSourceAuth (@ApplicationContext context: Context) : LocalDataSourceAuth {
        return LocalDataSourceAuth(
            sharedPreferences = SharedPreferencesFactory().createSharedPreferences(
                context
            ), context.dataStore
        )
    }
}