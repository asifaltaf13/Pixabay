package com.challenge.pixabay.data.remote.dto

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PixabayPhoto(
    // overridden
    @SerialName("id") override var id: Int?,
    @SerialName("tags") override var tags: String? = null,
    @SerialName("previewURL") override var previewURL: String? = null,
    @SerialName("previewWidth") override var previewWidth: Int? = null,
    @SerialName("previewHeight") override var previewHeight: Int? = null,
    @SerialName("largeImageURL") override var largeImageURL: String? = null,
    @SerialName("imageWidth") override var imageWidth: Int? = null,
    @SerialName("imageHeight") override var imageHeight: Int? = null,
    @SerialName("downloads") override var downloads: Int? = null,
    @SerialName("likes") override var likes: Int? = null,
    @SerialName("comments") override var comments: Int? = null,
    @SerialName("user") override var user: String? = null,
    // rest
    @SerialName("pageURL") var pageURL: String? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("webformatURL") var webformatURL: String? = null,
    @SerialName("webformatWidth") var webformatWidth: Int? = null,
    @SerialName("webformatHeight") var webformatHeight: Int? = null,
    @SerialName("imageSize") var imageSize: Int? = null,
    @SerialName("views") var views: Int? = null,
    @SerialName("collections") var collections: Int? = null,
    @SerialName("user_id") var userId: Int? = null,
    @SerialName("userImageURL") var userImageURL: String? = null
) : Photo()
