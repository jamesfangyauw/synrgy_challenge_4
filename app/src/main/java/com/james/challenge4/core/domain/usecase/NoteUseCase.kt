package com.james.challenge4.core.domain.usecase

import com.james.challenge4.core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteUseCase {
    fun getAllNote() : Flow<List<Note>>?

    suspend fun addEditNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

}