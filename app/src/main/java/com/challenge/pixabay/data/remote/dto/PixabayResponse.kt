package com.challenge.pixabay.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PixabayResponse(
    @SerialName("total") val total: Int? = null,
    @SerialName("totalHits") val totalHits: Int? = null,
    @SerialName("hits") val photos: List<PixabayPhoto> = arrayListOf()
)
