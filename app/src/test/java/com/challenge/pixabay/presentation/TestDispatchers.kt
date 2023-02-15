package com.challenge.pixabay.presentation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatchers : IDispatchers {

    val standardTestDispatchers = StandardTestDispatcher()

    override val main: CoroutineDispatcher
        get() = standardTestDispatchers
    override val io: CoroutineDispatcher
        get() = standardTestDispatchers
    override val default: CoroutineDispatcher
        get() = standardTestDispatchers
}