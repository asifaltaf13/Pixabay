package com.challenge.pixabay.presentation.photos_list

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.presentation.AppUiState
import com.challenge.pixabay.presentation.photo_detail.PhotoDetailScreen

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun ListOnlyContent(
    photos: List<IPhoto>,
    uiState: AppUiState,
    onPhotoCardClick: (IPhoto) -> Unit,
    onBackPressedFromDetails: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (uiState.isShowingHomepage) {

        // Start the animation immediately.
        val visibleState = remember { MutableTransitionState(false).apply { targetState = true } }
        AnimatedVisibility(
            visibleState = visibleState, enter = fadeIn(
                animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
            ), exit = fadeOut()
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(250.dp),
                modifier = modifier.fillMaxWidth(),
                contentPadding = PaddingValues(4.dp)
            ) {
                itemsIndexed(photos) { _, photo ->
                    ImageCard(
                        photo = photo,
                        onCardClick = { onPhotoCardClick(photo) },
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                            // Animate each list item to slide in vertically
                            .animateEnterExit(
                                enter = slideInVertically(
                                    animationSpec = spring(
                                        stiffness = Spring.StiffnessLow,
                                        dampingRatio = Spring.DampingRatioMediumBouncy
                                    ), initialOffsetY = { fullHeight -> fullHeight })
                            )
                    )
                }
            }
        }
    } else {
        PhotoDetailScreen(
            uiState = uiState, onBackPressed = onBackPressedFromDetails, modifier = modifier
        )
    }
}
