package com.challenge.pixabay.data.common

import com.challenge.pixabay.data.remote.dto.PixabayPhoto

object LocalDataProvider {

    val defaultPhoto = PixabayPhoto(
        id = -1,
        tags = "",
        previewURL = "",
        previewWidth = -1,
        previewHeight = -1,
        largeImageURL = "",
        imageWidth = -1,
        downloads = -1,
        imageHeight = -1,
        likes = -1,
        comments = -1,
        user = "",
        pageURL = "",
        type = "",
        webformatURL = "",
        webformatHeight = -1,
        webformatWidth = -1,
        imageSize = -1,
        views = -1,
        collections = -1,
        userId = -1,
        userImageURL = ""
    )
}
