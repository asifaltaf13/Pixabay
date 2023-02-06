package com.challenge.pixabay.domain.use_case.get_default_photo

import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.domain.repository.IPhotosRepository

class GetDefaultPhotoUseCase(
    private val repository: IPhotosRepository
) {

    operator fun invoke(): IPhoto {
        return repository.getDefaultPhoto()
    }
}