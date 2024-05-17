package com.james.challenge4.core.di.auth

import android.content.Context
import com.james.challenge4.core.SharedPreferencesFactory
import com.james.challenge4.core.data.source.local.auth.LocalDataSourceAuth
import com.james.challenge4.core.dataStore
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
        return LocalDataSourceAuth(sharedPreferences = SharedPreferencesFactory().createSharedPreferences(context), context.dataStore)
    }
}