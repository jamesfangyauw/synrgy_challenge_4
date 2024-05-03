package com.james.challenge_4.domain.usecase

import com.james.challenge_4.domain.model.Note
import com.james.challenge_4.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteInteractor @Inject constructor(private val noteRepository: INoteRepository) : NoteUseCase {
    override fun getAllNote(): Flow<List<Note>>? {
        return flow {noteRepository.getAllNote()  }
    }

    override suspend fun addEditNote(note: Note) {
        noteRepository.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteRepository.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteRepository.deleteNote(note)
    }
}