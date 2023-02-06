package com.challenge.pixabay.data.remote.dto

import com.challenge.pixabay.domain.model.IPhoto

data class FakePhoto(
    override var id: Int?,
    override var tags: String?,
    override var previewURL: String?,
    override var previewWidth: Int?,
    override var previewHeight: Int?,
    override var largeImageURL: String?,
    override var imageWidth: Int?,
    override var imageHeight: Int?,
    override var downloads: Int?,
    override var likes: Int?,
    override var comments: Int?,
    override var user: String?
) : IPhoto
