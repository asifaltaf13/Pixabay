package com.challenge.pixabay.presentation.photos_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.R
import com.challenge.pixabay.common.TestTags
import com.challenge.pixabay.presentation.common.loading.Loading

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageCard(
    photo: IPhoto,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .testTag(TestTags.ImageCard)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        onClick = onCardClick,
    ) {
        Box(modifier = Modifier
            .height(200.dp)
            .width(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Loading()

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(photo.previewURL)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.pixabay_photo),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                //error = painterResource(id = R.drawable.ic_broken_image),
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 299f,
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    photo.user?.let {
                        Text(text = it,
                            style = MaterialTheme.typography.h3,
                            color = Color.White
                        )
                    }
                    photo.tags?.let {
                        Text(text = it.uppercase(),
                            style = MaterialTheme.typography.caption,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}