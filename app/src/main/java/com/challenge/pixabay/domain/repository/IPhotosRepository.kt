package com.challenge.pixabay.domain.repository

import com.challenge.pixabay.domain.model.IPhoto

interface IPhotosRepository {
    suspend fun getPhotos(searchTerm: String): List<IPhoto>
    fun getDefaultPhoto(): IPhoto
}

