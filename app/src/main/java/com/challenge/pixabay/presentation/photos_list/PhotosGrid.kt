package com.challenge.pixabay.presentation.photos_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.presentation.AppUiState
import com.challenge.pixabay.presentation.AppContentType

@Composable
fun PhotosGrid(
    photos: List<IPhoto>,
    uiState: AppUiState,
    contentType: AppContentType,
    onPhotoCardClick: (IPhoto) -> Unit,
    onBackPressedFromDetails: () -> Unit,
    modifier: Modifier = Modifier
) {

    if (contentType == AppContentType.LIST_ONLY) {
        ListOnlyContent(
            photos = photos,
            uiState = uiState,
            onPhotoCardClick = onPhotoCardClick,
            onBackPressedFromDetails = onBackPressedFromDetails,
            modifier = modifier
        )
    } else {
        ListAndDetailContent(
            photos = photos,
            uiState = uiState,
            onCardClick = onPhotoCardClick,
            modifier = modifier
        )
    }
}


