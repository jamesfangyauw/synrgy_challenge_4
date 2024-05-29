package com.james.challenge4.di.note

import com.james.challenge4.data.repository.NoteRepository
import com.james.challenge4.domain.repository.INoteRepository
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