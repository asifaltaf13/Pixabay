package com.challenge.pixabay.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.challenge.pixabay.PixabayApplication
import com.challenge.pixabay.common.Constants.defaultSearchTerm
import com.challenge.pixabay.domain.CommonUtils
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.domain.use_case.get_default_photo.GetDefaultPhotoUseCase
import com.challenge.pixabay.domain.use_case.get_photos.GetPhotosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val getDefaultPhotoUseCase: GetDefaultPhotoUseCase,
    private val dispatchers: IDispatchers
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AppUiState(
            currentSelectedPhoto = getDefaultPhoto(),
            searchTerm = ""
        )
    )
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    // for temporary holding the touched photo until the user confirms the dialog
    private lateinit var clickedPhoto: IPhoto

    init {
        getPhotos(searchTerm = defaultSearchTerm)
    }

    fun getPhotos(searchTerm: String) {
        val processedValue = CommonUtils.preprocessSearchTerm(searchTerm)
        Log.d("SearchTerm (preprocessed): ", processedValue)

        viewModelScope.launch(dispatchers.main) {
            _uiState.update { currentState ->
                currentState.copy(
                    requestStatus = getPhotosUseCase(searchTerm = searchTerm)
                )
            }
        }
    }

    private fun getDefaultPhoto(): IPhoto {
        return getDefaultPhotoUseCase()
    }

    fun tryAgainGetPhotos() {
        getPhotos(searchTerm = uiState.value.searchTerm)
    }

    fun updateSearchWidgetState(searchWidgetState: SearchWidgetState) {
        _uiState.update { currentState ->
            currentState.copy(
                searchWidgetState = searchWidgetState
            )
        }
    }

    fun updateSearchTermState(searchTerm: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchTerm = searchTerm
            )
        }
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                currentSelectedPhoto = getDefaultPhoto(),
                isShowingHomepage = true,
                showDialog = false
            )
        }
    }

    fun onOpenDialogClicked(photo: IPhoto) {
        _uiState.update {
            it.copy(
                showDialog = true
            )
        }
        clickedPhoto = photo // keeping the photo until user confirmation
    }

    fun onDialogConfirm() {
        _uiState.update {
            it.copy(
                showDialog = false,
                isShowingHomepage = false,
                currentSelectedPhoto = clickedPhoto
            )
        }
    }

    fun onDialogDismiss() {
        resetHomeScreenStates()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {

            initializer {
                val application = (this[APPLICATION_KEY] as PixabayApplication)
                val photosRepository = application.appModule.providePhotosRepository
                AppViewModel(
                    getPhotosUseCase = GetPhotosUseCase(repository = photosRepository),
                    getDefaultPhotoUseCase = GetDefaultPhotoUseCase(repository = photosRepository),
                    dispatchers = DefaultDispatchers()
                )
            }
        }
    }
}
