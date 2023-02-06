package com.challenge.pixabay.presentation

import com.challenge.pixabay.presentation.IDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher

class TestDispatchers : IDispatchers {

    @OptIn(ExperimentalCoroutinesApi::class)
    val standardTestDispatchers = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val main: CoroutineDispatcher
        get() = standardTestDispatchers
    override val io: CoroutineDispatcher
        get() = standardTestDispatchers
    override val default: CoroutineDispatcher
        get() = standardTestDispatchers
}