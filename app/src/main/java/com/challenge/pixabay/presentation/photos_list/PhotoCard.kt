package com.challenge.pixabay.presentation.photos_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.challenge.pixabay.R
import com.challenge.pixabay.common.TestTags
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.presentation.common.loading.Loading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCard(
    photo: IPhoto, onCardClick: () -> Unit, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .testTag(TestTags.ImageCard)
            .fillMaxWidth(),
        //colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onCardClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            SubcomposeAsyncImage(
                loading = { Loading(modifier.padding(vertical = 50.dp)) },
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(photo.largeImageURL)
                    .crossfade(500)
                    .build(),
                contentDescription = stringResource(id = R.string.pixabay_photo),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                // error = painterResource(id = R.drawable.ic_broken_image),
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.6f))
                    .align(Alignment.BottomStart)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 2.dp,
                            bottom = 8.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                ) {
                    Text(
                        text = photo.user,
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = photo.tags.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}
