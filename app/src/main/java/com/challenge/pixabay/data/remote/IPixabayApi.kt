package com.challenge.pixabay.data.remote

import com.challenge.pixabay.BuildConfig
import com.challenge.pixabay.common.Constants.defaultSearchTerm
import com.challenge.pixabay.data.common.Constants
import com.challenge.pixabay.data.remote.dto.PixabayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IPixabayApi {

    @GET("api")
    suspend fun searchImages(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") searchTerm: String = defaultSearchTerm,
        @Query("image_type") imageType: String = Constants.imageType,
        @Query("order") order: String = Constants.order,
        @Query("per_page") perPage: Int = Constants.perPage
        // @Query("orientation") orientation: String = "horizontal"
    ): Response<PixabayResponse>
}
