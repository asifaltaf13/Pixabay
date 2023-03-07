package com.challenge.pixabay.presentation.photos_app

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.challenge.pixabay.common.IRequestStatus
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.presentation.AppContentType
import com.challenge.pixabay.presentation.AppUiState
import com.challenge.pixabay.presentation.AppViewModel
import com.challenge.pixabay.presentation.SearchWidgetState
import com.challenge.pixabay.presentation.photos_app.components.ConfirmationAlertDialog
import com.challenge.pixabay.presentation.photos_app.components.MainAppBar
import com.challenge.pixabay.presentation.photos_app.components.OnLifecycleEvent
import com.challenge.pixabay.presentation.photos_app.components.SearchFloatingActionButton
import com.challenge.pixabay.presentation.photos_list.PhotoListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosAppScreen(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: AppViewModel = viewModel(factory = AppViewModel.Factory)
    val uiState: AppUiState by viewModel.uiState.collectAsState()

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact -> AppContentType.LIST_ONLY
        WindowWidthSizeClass.Medium -> AppContentType.LIST_ONLY
        WindowWidthSizeClass.Expanded -> AppContentType.LIST_AND_DETAIL
        else -> AppContentType.LIST_ONLY
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                searchWidgetState = uiState.searchWidgetState,
                searchTextState = uiState.searchTerm,
                onTextChange = {
                    viewModel.updateSearchTermState(searchTerm = it)
                },
                onCloseClicked = {
                    viewModel.updateSearchTermState(searchTerm = "")
                    viewModel.updateSearchWidgetState(searchWidgetState = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    Log.d("SearchTerm (input): ", it)
                    viewModel.getPhotos(it)
                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(searchWidgetState = SearchWidgetState.OPENED)
                }
            )
        },
        floatingActionButton = {
            SearchFloatingActionButton(
                onSearchClicked = {
                    viewModel.updateSearchWidgetState(searchWidgetState = SearchWidgetState.OPENED)
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            //color = MaterialTheme.colorScheme.background
        ) {
            PhotoListScreen(
                contentType = contentType,
                uiState = uiState,
                onPhotoCardClick = { photo: IPhoto ->
                    viewModel.onOpenDialogClicked(photo = photo)
                    Log.d("HomeScreen", "card pressed")
                },
                onBackPressedFromDetails = {
                    viewModel.resetHomeScreenStates()
                },
                modifier = modifier
            )

            ConfirmationAlertDialog(
                show = uiState.showDialog,
                onDismiss = viewModel::onDialogDismiss, // passing function as a lambda
                onConfirm = viewModel::onDialogConfirm
            )
        }
    }

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_RESUME && uiState.requestStatus is IRequestStatus.Error) {
            viewModel.tryAgainGetPhotos()
        }
    }
}
