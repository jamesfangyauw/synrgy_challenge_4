package com.james.challenge_4.core.data.source.local.note

import com.james.challenge_4.core.data.source.local.NoteDao
import com.james.challenge_4.core.data.source.local.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceNote @Inject constructor(private val noteDao: NoteDao) {
    fun getAllNote() : Flow<List<NoteEntity>>?{
       return noteDao.getAllNote()

    }

    suspend fun addNote(note: NoteEntity){
        noteDao.insertNote(note)
    }

    suspend fun updateNote(note: NoteEntity){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: NoteEntity){
        noteDao.deleteNote(note)
    }
}