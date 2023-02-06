package com.challenge.pixabay.presentation.photos_app.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.challenge.pixabay.R
import com.challenge.pixabay.common.TestTags

@Composable
fun SearchFloatingActionButton(
    onSearchClicked: () -> Unit
) {
    FloatingActionButton(
        modifier = Modifier.testTag(TestTags.SearchFloatingActionButton),
        backgroundColor = colorResource(id = R.color.green),
        onClick = {
            onSearchClicked()
        },
        content = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.search_floating_action_button),
                tint = Color.White,
            )
        }
    )
}