package com.challenge.pixabay.presentation.photo_detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.challenge.pixabay.presentation.AppUiState
import com.challenge.pixabay.R

@Composable
fun PhotoDetailScreen(
    uiState: AppUiState,
    onBackPressed: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val photo = uiState.currentSelectedPhoto

    BackHandler {
        onBackPressed()
    }

    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    if (uiState.isShowingHomepage) {
                        Image(
                            painter = painterResource(id = R.drawable.please_select_an_image),
                            contentDescription = stringResource(id = R.string.pixabay_photo),
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .padding(horizontal = 48.dp)
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop,
                        )
                    } else {
                        LargePhoto(photo, painterResource(id = R.drawable.failed_to_load_the_image))
                        DetailContent(photo)
                    }
                }
            }
        }
    }
}