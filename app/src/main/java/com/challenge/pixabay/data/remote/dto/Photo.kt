package com.challenge.pixabay.data.remote.dto

import com.challenge.pixabay.domain.model.IPhoto

/**
 * Photo response class should extend this class
 */

sealed class Photo : IPhoto {
    abstract override var id: Int?
    abstract override var tags: String?
    abstract override var previewURL: String?
    abstract override var previewWidth: Int?
    abstract override var previewHeight: Int?
    abstract override var largeImageURL: String?
    abstract override var imageWidth: Int?
    abstract override var imageHeight: Int?
    abstract override var downloads: Int?
    abstract override var likes: Int?
    abstract override var comments: Int?
    abstract override var user: String?
}
