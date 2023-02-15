package com.challenge.pixabay.presentation

import com.challenge.pixabay.common.IRequestStatus
import com.challenge.pixabay.data.repository.FakeRepository
import com.challenge.pixabay.domain.use_case.get_default_photo.GetDefaultPhotoUseCase
import com.challenge.pixabay.domain.use_case.get_photos.GetPhotosUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class AppViewModelTest {

    private lateinit var viewModel: AppViewModel
    private lateinit var testDispatchers: TestDispatchers
    lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        val getPhotosUseCase = GetPhotosUseCase(repository = fakeRepository)
        val getDefaultPhotoUseCase = GetDefaultPhotoUseCase(repository = fakeRepository)
        testDispatchers = TestDispatchers()

        viewModel = AppViewModel(
            getPhotosUseCase = getPhotosUseCase,
            getDefaultPhotoUseCase = getDefaultPhotoUseCase,
            dispatchers = testDispatchers
        )

        MockitoAnnotations.initMocks(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `app view model, view model attempts to get photos, expected request status is success`() =
        runTest {
            assertThat(viewModel.uiState.value.requestStatus is IRequestStatus.Loading).isTrue()
            viewModel.getPhotos("")
            testDispatchers.standardTestDispatchers.scheduler.advanceUntilIdle()
            assertThat(viewModel.uiState.value.requestStatus is IRequestStatus.Success).isTrue()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `app view model, view model attempts to get photos, expected request status is error`() =
        runTest {
            assertThat(viewModel.uiState.value.requestStatus is IRequestStatus.Loading).isTrue()
            viewModel.getPhotos(fakeRepository.fakeHttpExceptionTerm)
            testDispatchers.standardTestDispatchers.scheduler.advanceUntilIdle()
            assertThat(viewModel.uiState.value.requestStatus is IRequestStatus.Error).isTrue()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `app view model, view model attempts to get photos, expected list with 2 photos`() =
        runTest {
            assertThat(viewModel.uiState.value.requestStatus is IRequestStatus.Loading).isTrue()

            viewModel.getPhotos("two")
            testDispatchers.standardTestDispatchers.scheduler.advanceUntilIdle()
            assertThat(viewModel.uiState.value.requestStatus is IRequestStatus.Success).isTrue()

            val requestStatus: IRequestStatus = viewModel.uiState.value.requestStatus
            if (requestStatus is IRequestStatus.Success) {
                assertThat(2 == requestStatus.photos.size)
            }
        }
}
