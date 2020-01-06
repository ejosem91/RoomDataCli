package com.example.core.usecase

import com.example.core.repository.PhotoDataRepository

class PhotoUseCase(private val photoDataRepository: PhotoDataRepository) {
    suspend  fun getPhotoData() = photoDataRepository.getDataPhoto()
}