package com.challenge.pixabay.data.remote.dto

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PixabayPhoto(
    // overridden
    @SerialName("id") override var id: Int,
    @SerialName("tags") override var tags: String,
    @SerialName("previewURL") override var previewURL: String,
    @SerialName("previewWidth") override var previewWidth: Int,
    @SerialName("previewHeight") override var previewHeight: Int,
    @SerialName("largeImageURL") override var largeImageURL: String,
    @SerialName("imageWidth") override var imageWidth: Int,
    @SerialName("imageHeight") override var imageHeight: Int,
    @SerialName("downloads") override var downloads: Int,
    @SerialName("likes") override var likes: Int,
    @SerialName("comments") override var comments: Int,
    @SerialName("user") override var user: String,
    // rest
    @SerialName("pageURL") var pageURL: String,
    @SerialName("type") var type: String,
    @SerialName("webformatURL") var webformatURL: String,
    @SerialName("webformatWidth") var webformatWidth: Int,
    @SerialName("webformatHeight") var webformatHeight: Int,
    @SerialName("imageSize") var imageSize: Int,
    @SerialName("views") var views: Int,
    @SerialName("collections") var collections: Int,
    @SerialName("user_id") var userId: Int,
    @SerialName("userImageURL") var userImageURL: String
) : Photo()
