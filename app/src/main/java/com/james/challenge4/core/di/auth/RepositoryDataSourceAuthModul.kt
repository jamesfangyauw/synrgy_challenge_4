package com.james.challenge4.core.di.auth

import com.james.challenge4.core.data.repository.AuthRepository
import com.james.challenge4.core.domain.repository.IAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [LocalDataSourceAuthModul::class, RemoteDataSourceAuthModul::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryDataSourceAuthModul {
    @Binds
    abstract fun provideRepositoryAuth(authRepository: AuthRepository) : IAuthRepository
}