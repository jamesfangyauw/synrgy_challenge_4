package com.james.challenge4.di.photo

import com.james.challenge4.data.repository.PhotoRepository
import com.james.challenge4.domain.repository.IPhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [LocalDataSourcePhotoModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryPhotoModule {
    @Binds
    abstract fun provideRepositoryPhotoModule(photoRepository: PhotoRepository) : IPhotoRepository
}