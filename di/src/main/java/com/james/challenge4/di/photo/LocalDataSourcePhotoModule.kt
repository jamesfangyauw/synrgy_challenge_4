package com.james.challenge4.di.photo

import android.content.Context
import com.james.challenge4.data.dataStore
import com.james.challenge4.data.source.local.photo.LocalDataSourcePhoto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourcePhotoModule {

    @Provides
    fun provideLocalDataSourcePhoto (@ApplicationContext context: Context) : LocalDataSourcePhoto {
        return LocalDataSourcePhoto(context.dataStore)
    }
}