package com.challenge.pixabay.data.remote.dto

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PixabayResponse(
    @SerialName("total") var total: Int? = null,
    @SerialName("totalHits") var totalHits: Int? = null,
    @SerialName("hits") var photos: List<PixabayPhoto> = arrayListOf()
)
