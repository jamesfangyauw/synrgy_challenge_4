package com.james.challenge4.data.repository

import com.james.challenge4.data.source.local.note.NoteEntity
import com.james.challenge4.data.source.local.note.LocalDataSourceNote
import com.james.challenge4.data.utils.DataMapper.mapEntitiesToDomain
import com.james.challenge4.domain.model.Note
import com.james.challenge4.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
    private val localDataSourceNote: LocalDataSourceNote
) : INoteRepository {
    override fun getAllNote(): Flow<List<Note>>? {
        return localDataSourceNote.getAllNote()?.map {
            mapEntitiesToDomain(it)
        }
    }

    override suspend fun addNote(note: Note) {
        localDataSourceNote.addNote(NoteEntity(note.id, note.title, note.content))
    }

    override suspend fun updateNote(note: Note) {
        localDataSourceNote.updateNote(NoteEntity(note.id, note.title, note.content))
    }

    override suspend fun deleteNote(note: Note) {
        localDataSourceNote.deleteNote(NoteEntity(note.id, note.title, note.content))
    }


}

