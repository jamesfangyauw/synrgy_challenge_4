package com.james.challenge_4.core.data.source.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface NoteDao {

//    @Query("SELECT * FROM notesTable")
//    fun getAllTourism(): Flow<List<TourismEntity>>
//
//    @Query("SELECT * FROM tourism where isFavorite = 1")
//    fun getFavoriteTourism(): Flow<List<TourismEntity>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTourism(tourism: List<TourismEntity>)
//
//    @Update
//    fun updateFavoriteTourism(tourism: TourismEntity)
}