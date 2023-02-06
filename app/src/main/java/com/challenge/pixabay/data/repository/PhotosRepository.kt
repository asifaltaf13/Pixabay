package com.challenge.pixabay.data.repository

import com.challenge.pixabay.data.common.LocalDataProvider
import com.challenge.pixabay.data.remote.IPixabayApi
import com.challenge.pixabay.data.remote.dto.PixabayResponse
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.domain.repository.IPhotosRepository

class PhotosRepository(
    private val pixabayApi: IPixabayApi
) : IPhotosRepository {

    override suspend fun getPhotos(searchTerm: String): List<IPhoto> {

        val response = pixabayApi.searchImages(searchTerm = searchTerm)
        val pixabayResponse : PixabayResponse? = response.body()
        return pixabayResponse?.photos ?: emptyList()
    }

    override fun getDefaultPhoto(): IPhoto {
        return LocalDataProvider.defaultPhoto
    }

}