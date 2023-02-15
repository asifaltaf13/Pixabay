package com.challenge.pixabay.domain.use_case.get_photos

import com.challenge.pixabay.common.IRequestStatus
import com.challenge.pixabay.data.common.LocalDataProvider.defaultPhoto
import com.challenge.pixabay.data.repository.FakeRepository
import com.challenge.pixabay.domain.model.IPhoto
import com.challenge.pixabay.domain.use_case.get_default_photo.GetDefaultPhotoUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPhotosUseCaseTest {

    private lateinit var getPhotosUseCase: GetPhotosUseCase
    private lateinit var getDefaultPhotoUseCase: GetDefaultPhotoUseCase

    private lateinit var fakeRepository: FakeRepository

    @Before
    internal fun setup() {
        fakeRepository = FakeRepository()
        getPhotosUseCase = GetPhotosUseCase(fakeRepository)
        getDefaultPhotoUseCase = GetDefaultPhotoUseCase(fakeRepository)
    }

    @Test
    fun `get photos use case, get all photos, correct returned list`() = runBlocking {
        val requestStatus: IRequestStatus = getPhotosUseCase("")

        lateinit var returnedList: List<IPhoto>
        if (requestStatus is IRequestStatus.Success) {
            returnedList = requestStatus.photos
        }

        for (i in returnedList.indices) {
            assertThat(returnedList[i].id == i).isTrue()
        }
    }

    @Test
    fun `get photos use case, get photos, throws HttpException with localized message`() =
        runBlocking {
            val expectedErrorMessage = fakeRepository.fakeHttpExceptionMessage
            val requestStatus: IRequestStatus =
                getPhotosUseCase(fakeRepository.fakeHttpExceptionTerm)
            lateinit var resultErrorMessage: String

            if (requestStatus is IRequestStatus.Error) {
                resultErrorMessage = requestStatus.message
            }

            assertThat(
                resultErrorMessage == expectedErrorMessage
            ).isTrue()
        }

    @Test
    fun `get photos use case, get photos, throws IoException`() = runBlocking {
        val expectedErrorMessage = "Couldn't reach server, Check your internet connection."
        val requestStatus: IRequestStatus = getPhotosUseCase(fakeRepository.fakeIoExceptionTerm)
        lateinit var resultErrorMessage: String

        if (requestStatus is IRequestStatus.Error) {
            resultErrorMessage = requestStatus.message
        }

        assertThat(
            resultErrorMessage == expectedErrorMessage
        ).isTrue()
    }

    @Test
    fun `get photos use case, get specific photos, correct returned list`() = runBlocking {
        val requestStatus: IRequestStatus = getPhotosUseCase("two")

        lateinit var returnedList: List<IPhoto>
        if (requestStatus is IRequestStatus.Success) {
            returnedList = requestStatus.photos
        }

        for (i in returnedList.indices) {
            assertThat(returnedList.size == 2).isTrue()
        }
    }

    @Test
    fun `get photos use case, get default photo`() {
        val returnedPhoto = getDefaultPhotoUseCase()
        assertThat(returnedPhoto == defaultPhoto).isTrue()
    }
}
