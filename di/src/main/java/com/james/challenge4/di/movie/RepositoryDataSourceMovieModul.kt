package com.james.challenge4.di.movie

import com.james.challenge4.data.repository.MovieRepository
import com.james.challenge4.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryDataSourceMovieModul {
    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository) : IMovieRepository
}