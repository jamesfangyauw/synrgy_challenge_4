package com.james.challenge_4.core.di

import com.james.challenge_4.core.data.source.AuthRepository
import com.james.challenge_4.core.data.source.NoteRepository
import com.james.challenge_4.domain.repository.IAuthRepository
import com.james.challenge_4.domain.repository.INoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryDataSourceNoteModul {
    @Binds
    abstract fun provideRepositoryNote(noteRepository: NoteRepository) : INoteRepository
}