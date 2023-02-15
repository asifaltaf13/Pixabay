package com.challenge.pixabay.presentation

import com.challenge.pixabay.common.Constants.defaultSearchTerm
import com.challenge.pixabay.common.IRequestStatus
import com.challenge.pixabay.domain.model.IPhoto

data class AppUiState(

    // search related
    val searchTerm: String = defaultSearchTerm,
    val orientation: String = "horizontal",
    val order: String = "latest",
    val imageType: String = "photo",

    // app related
    val requestStatus: IRequestStatus = IRequestStatus.Loading,
    val currentSelectedPhoto: IPhoto,
    val isShowingHomepage: Boolean = true,
    val showDialog: Boolean = false,
    val searchWidgetState: SearchWidgetState = SearchWidgetState.CLOSED
)

enum class SearchWidgetState {
    OPENED, CLOSED
}

enum class AppContentType {
    LIST_ONLY, LIST_AND_DETAIL
}