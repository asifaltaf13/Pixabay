package com.challenge.pixabay.presentation.photo_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.challenge.pixabay.R
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.presentation.common.loading.Loading

@Composable
internal fun LargePhoto(
    photo: IPhoto,
    error: Painter? = null
) {
    Box {
        Loading(
            modifier = Modifier.height(250.dp)
        )

        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxSize(),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.largeImageURL)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.pixabay_photo),
            contentScale = ContentScale.Crop,
            // placeholder = painterResource(id = R.drawable.loading_img),
            error = error
        )
    }
}
