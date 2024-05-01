package com.james.challenge_4.core.di

import android.content.Context
import android.content.SharedPreferences
import com.james.challenge_4.core.SharedPreferencesFactory
import com.james.challenge_4.core.data.source.local.auth.LocalDataSourceAuth
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
        return LocalDataSourceAuth(sharedPreferences = SharedPreferencesFactory().createSharedPreferences(context))
    }
}