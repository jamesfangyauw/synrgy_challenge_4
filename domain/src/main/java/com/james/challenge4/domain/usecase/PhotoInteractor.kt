package com.james.challenge4.domain.usecase

import com.james.challenge4.domain.repository.IPhotoRepository
import javax.inject.Inject

class PhotoInteractor @Inject constructor(private val photoRepository: IPhotoRepository) : PhotoUseCase {
    override suspend fun savePhoto(photo: String) {
        photoRepository.savePhoto(photo)
    }

    override suspend fun loadPhoto(): String? {
        return photoRepository.loadPhoto()
    }
}