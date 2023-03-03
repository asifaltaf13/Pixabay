package com.challenge.pixabay.domain.model

interface IPhoto {
    var id: Int
    var tags: String
    var previewURL: String
    var previewWidth: Int
    var previewHeight: Int
    var largeImageURL: String
    var imageWidth: Int
    var imageHeight: Int
    var downloads: Int
    var likes: Int
    var comments: Int
    var user: String
}
