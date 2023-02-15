package com.challenge.pixabay.data.repository

import com.challenge.pixabay.data.common.LocalDataProvider
import com.challenge.pixabay.data.remote.dto.FakePhoto
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.domain.repository.IPhotosRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class FakeRepository : IPhotosRepository {

    private val fakePhotos = mutableListOf<IPhoto>()

    val fakeHttpExceptionTerm = "ThrowHttpException"
    val fakeIoExceptionTerm = "ThrowIoException"

    val fakeHttpExceptionMessage = "FakeHttpException"

    init {
        val photosToInsert = mutableListOf<IPhoto>()

        // 1 one, 2 twos, 3 threes in the list
        // total 6
        val searchTerms = listOf("one", "two", "two", "three", "three", "three")

        searchTerms.forEachIndexed { id, searchTerm ->
            photosToInsert.add(
                FakePhoto(
                    id = id,
                    tags = searchTerm,
                    previewURL = searchTerm,
                    previewWidth = id,
                    previewHeight = id,
                    largeImageURL = searchTerm,
                    imageWidth = id,
                    imageHeight = id,
                    downloads = id,
                    likes = id,
                    comments = id,
                    user = searchTerm
                )
            )
        }

        fakePhotos.addAll(photosToInsert)
    }

    override suspend fun getPhotos(searchTerm: String): List<IPhoto> {
        if (searchTerm == fakeHttpExceptionTerm) {
            throw FakeHttpException(localizedMessage = fakeHttpExceptionMessage)
        } else if (searchTerm == fakeIoExceptionTerm) {
            throw FakeIOException()
        }

        if (searchTerm.isEmpty()) return fakePhotos
        if (searchTerm == "fruits") return fakePhotos

        return fakePhotos.filter { photo ->
            photo.user == searchTerm
        }
    }

    override fun getDefaultPhoto(): IPhoto {
        return LocalDataProvider.defaultPhoto
    }
}

class FakeHttpException(localizedMessage: String) : HttpException(
    Response.error<ResponseBody>(
        500,
        "some content".toResponseBody("plain/text".toMediaTypeOrNull())
    )
) {
    override val message = localizedMessage
}

class FakeIOException : IOException()
