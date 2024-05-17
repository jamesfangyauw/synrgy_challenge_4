package com.james.challenge4.core.data.source.local.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    var id : Int?,

    @ColumnInfo(name = "title")
    val title : String,

    @ColumnInfo(name = "content")
    val content : String
)
