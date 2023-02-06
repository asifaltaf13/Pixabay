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
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.presentation.photo_detail.components.HyperlinkText
import com.challenge.pixabay.R

@Composable
internal fun DetailContent(
    photo: IPhoto,
) {
    Column {

        User(photo.user)

        photo.tags?.let { DetailProperty(stringResource(R.string.tags_heading), it) }
        photo.likes?.let { DetailProperty(stringResource(R.string.likes_heading), it.toString()) }
        photo.downloads?.let {
            DetailProperty(
                stringResource(R.string.downloads_heading),
                it.toString()
            )
        }
        photo.comments?.let {
            DetailProperty(
                stringResource(R.string.comments_heading),
                it.toString()
            )
        }

        photo.largeImageURL?.let {
            DetailProperty(
                label = "Image URL:",
                value = it,
                isLink = true,
            )
        }
    }
}

@Composable
fun User(user: String?) {
    Column(modifier = Modifier.padding(16.dp)) {
        user?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DetailProperty(
    label: String,
    value: String,
    isLink: Boolean = false) {
    Column(modifier = Modifier.padding(
        start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.height(24.dp),
                style = MaterialTheme.typography.caption,
            )
        }

        if(isLink)
        {
            HyperlinkText(
                fullText = value,
                hyperLinks = mutableMapOf(value to value),
                linkTextColor = MaterialTheme.colors.primary
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