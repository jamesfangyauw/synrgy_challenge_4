package com.james.challenge4.data.repository

import com.james.challenge4.data.source.local.photo.LocalDataSourcePhoto
import com.james.challenge4.domain.repository.IPhotoRepository
import javax.inject.Inject

class PhotoRepository @Inject constructor(private val localDataSourcePhoto: LocalDataSourcePhoto): IPhotoRepository {
    override suspend fun savePhoto(photo: String) {
        localDataSourcePhoto.savePhoto(photo)
    }

    override suspend fun loadPhoto(): String? {
        return localDataSourcePhoto.loadPhoto()
    }

}