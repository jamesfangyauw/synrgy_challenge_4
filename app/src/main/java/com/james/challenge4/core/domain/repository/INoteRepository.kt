package com.james.challenge4.core.domain.repository

import com.james.challenge4.core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface INoteRepository {
    fun getAllNote() : Flow<List<Note>>?

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}