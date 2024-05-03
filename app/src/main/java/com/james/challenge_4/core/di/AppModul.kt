package com.james.challenge_4.core.di

import com.james.challenge_4.domain.usecase.AuthInteractor
import com.james.challenge_4.domain.usecase.AuthUseCase
import com.james.challenge_4.domain.usecase.NoteInteractor
import com.james.challenge_4.domain.usecase.NoteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModul {
    @Binds
    @ViewModelScoped
    abstract fun provideAuthUseCase(authInteractor: AuthInteractor):AuthUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideNoteUseCase(noteInteractor: NoteInteractor):NoteUseCase
}