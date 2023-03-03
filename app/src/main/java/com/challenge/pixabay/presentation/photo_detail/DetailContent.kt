package com.challenge.pixabay.presentation.photo_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.challenge.pixabay.R
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.presentation.photo_detail.components.HyperlinkText

@Composable
internal fun DetailContent(
    photo: IPhoto
) {
    Column {
        User(photo.user)
        DetailProperty(stringResource(R.string.tags_heading), photo.tags)
        DetailProperty(stringResource(R.string.likes_heading), photo.likes.toString())
        DetailProperty(stringResource(R.string.downloads_heading), photo.downloads.toString())
        DetailProperty(stringResource(R.string.comments_heading), photo.comments.toString())
        DetailProperty(label = "Image URL:", value = photo.largeImageURL, isLink = true)
    }
}

@Composable
fun User(user: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = user, style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun DetailProperty(
    label: String, value: String, isLink: Boolean = false
) {
    Column(
        modifier = Modifier.padding(
            start = 16.dp, end = 16.dp, bottom = 16.dp
        )
    ) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.height(24.dp),
                style = MaterialTheme.typography.caption
            )
        }

        if (isLink) {
            HyperlinkText(
                fullText = value, hyperLinks = mutableMapOf(value to value)
            )
        } else {
            Text(
                text = value,
                modifier = Modifier.height(24.dp),
                style = MaterialTheme.typography.body1
            )
        }
    }
}
