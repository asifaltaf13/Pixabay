package com.challenge.pixabay.domain.use_case.get_photos

import com.challenge.pixabay.common.IRequestStatus
import com.challenge.pixabay.domain.repository.IPhotosRepository
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException

class GetPhotosUseCase(
    private val repository: IPhotosRepository
) {
    suspend operator fun invoke(searchTerm: String): IRequestStatus {
        return try {
            val photos = repository.getPhotos(searchTerm = searchTerm)
            IRequestStatus.Success(photos = photos)
        } catch (e: HttpException) {
            IRequestStatus.Error(e.localizedMessage ?: "An unexpected error occurred.")
        } catch (e: IOException) {
            IRequestStatus.Error(message = "Couldn't reach server, Check your internet connection.")
        }
    }
}