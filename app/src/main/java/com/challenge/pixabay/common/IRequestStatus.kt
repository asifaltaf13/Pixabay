package com.challenge.pixabay.common

import com.challenge.pixabay.domain.model.IPhoto

sealed interface IRequestStatus {
    data class Success(val photos: List<IPhoto>) : IRequestStatus
    data class Error(val message: String) : IRequestStatus
    object Loading : IRequestStatus
}

