package com.challenge.pixabay.presentation.photos_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.challenge.pixabay.common.IRequestStatus
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.presentation.AppUiState
import com.challenge.pixabay.presentation.AppContentType
import com.challenge.pixabay.presentation.common.loading.Loading

@Composable
fun PhotoListScreen(
    contentType: AppContentType,
    uiState: AppUiState,
    onPhotoCardClick: (IPhoto) -> Unit,
    onBackPressedFromDetails: () -> Unit,
    modifier: Modifier = Modifier
) {

    when (val requestStatus = uiState.requestStatus) {
        is IRequestStatus.Success -> {
            PhotosGrid(
                photos = requestStatus.photos,
                uiState = uiState,
                contentType = contentType,
                onPhotoCardClick = onPhotoCardClick,
                onBackPressedFromDetails = onBackPressedFromDetails,
                modifier = modifier
            )
        }
        is IRequestStatus.Loading -> Loading(modifier)
        is IRequestStatus.Error -> Error(message = requestStatus.message, modifier = modifier)
    }
}