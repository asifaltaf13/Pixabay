package com.challenge.pixabay.presentation

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}
