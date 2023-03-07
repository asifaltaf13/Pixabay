package com.challenge.pixabay.presentation.photos_app.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.challenge.pixabay.R
import com.challenge.pixabay.common.TestTags

@Composable
fun SearchFloatingActionButton(
    onSearchClicked: () -> Unit
) {
    FloatingActionButton(
        modifier = Modifier.testTag(TestTags.SearchFloatingActionButton),
        onClick = {
            onSearchClicked()
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        content = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.search_floating_action_button),
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    )
}