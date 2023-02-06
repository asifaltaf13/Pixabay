package com.challenge.pixabay.di

import com.challenge.pixabay.data.repository.FakeRepository

class TestAppModule : IAppModule {

    override val providePhotosRepository: FakeRepository = FakeRepository()
}