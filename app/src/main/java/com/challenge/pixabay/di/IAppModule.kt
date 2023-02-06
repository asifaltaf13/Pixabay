package com.challenge.pixabay.di

import com.challenge.pixabay.domain.repository.IPhotosRepository

interface IAppModule {
    val providePhotosRepository: IPhotosRepository
}