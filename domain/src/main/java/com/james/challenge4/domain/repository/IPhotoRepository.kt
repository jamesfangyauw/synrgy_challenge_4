package com.james.challenge4.domain.repository

interface IPhotoRepository {
    suspend fun savePhoto(photo:String)
    suspend fun loadPhoto() : String?
}