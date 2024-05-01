package com.james.challenge_4.core.data.source.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notesTable")
data class NoteEntity(
    @PrimaryKey
    @ColumnInfo(name ="id")
    var id : Int,

    @ColumnInfo(name = "title")
    val title : String,

    @ColumnInfo(name = "content")
    val content : String
)
