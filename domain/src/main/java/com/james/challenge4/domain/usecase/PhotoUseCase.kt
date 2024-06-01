package com.james.challenge4.domain.usecase

interface PhotoUseCase {
    suspend fun savePhoto(photo:String)
    suspend fun loadPhoto() : String?
}