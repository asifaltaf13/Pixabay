package com.challenge.pixabay.presentation.photos_app.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.challenge.pixabay.R
import com.challenge.pixabay.common.TestTags

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    searchTerm: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
        //color = MaterialTheme.colors.primary
    ) {
        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester)
                .onFocusChanged { keyboardController?.show() }
                .testTag(TestTags.SearchAppBarText),
            value = searchTerm,
            onValueChange = {
                onTextChange(it)
            },
            label = {
                Text(
                    text = "Search Term"
                )
            },
            placeholder = {
                Text(
                    // modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Search here..."
                    // color = Color.White
                )
            },
            textStyle = TextStyle(fontSize = MaterialTheme.typography.titleMedium.fontSize),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.testTag(TestTags.SearchAppBarSearch),
                    //modifier = Modifier.alpha(ContentAlpha.medium),
                    onClick = {
                        onSearchClicked(searchTerm)
                        keyboardController?.hide()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_appbar_search_icon)
                        //tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.testTag(TestTags.SearchAppBarClose),
                    onClick = {
                        if (searchTerm.isNotEmpty()) {
                            onTextChange("")
                            keyboardController?.show()
                        } else {
                            onCloseClicked()
                            keyboardController?.hide()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.search_appbar_close_icon)
                        //tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(searchTerm)
                    keyboardController?.hide()
                }
            )
//            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color.Transparent,
//                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
//            )
        )

        // LaunchedEffect prevents endless focus request upon each recomposition
        LaunchedEffect(focusRequester) {
                focusRequester.requestFocus()
        }
    }
}