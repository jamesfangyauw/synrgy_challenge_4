package com.james.challenge4.core.di.auth

import android.content.Context
import com.james.challenge4.core.SharedPreferencesFactory
import com.james.challenge4.core.data.source.remote.auth.RemoteDataSourceAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceAuthModul {

    @Provides
    fun provideRemoteDataSourceAuth (@ApplicationContext context: Context) : RemoteDataSourceAuth {
        return RemoteDataSourceAuth(sharedPreferences = SharedPreferencesFactory().createSharedPreferences(context))
    }
}