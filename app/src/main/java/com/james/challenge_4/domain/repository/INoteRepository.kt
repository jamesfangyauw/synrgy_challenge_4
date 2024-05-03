package com.james.challenge_4.domain.repository

import com.james.challenge_4.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface INoteRepository {
    fun getAllNote() : Flow<List<Note>>?

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}