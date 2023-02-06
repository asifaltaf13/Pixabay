package com.challenge.pixabay.presentation.photos_list

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.presentation.AppUiState
import com.challenge.pixabay.presentation.photo_detail.PhotoDetailScreen

@Composable
fun ListAndDetailContent(
    photos: List<IPhoto>,
    uiState: AppUiState,
    onCardClick: (IPhoto) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        LazyVerticalGrid(
            modifier = Modifier.weight(1f),
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(4.dp),
        ) {
            itemsIndexed(photos) { _, photo ->
                ImageCard(
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                    photo = photo,
                    onCardClick = { onCardClick(photo) }
                )
            }
        }

        val activity = LocalContext.current as Activity
        PhotoDetailScreen(
            modifier = Modifier.weight(1f),
            uiState = uiState,
            onBackPressed = { activity.finish() }
        )
    }
}