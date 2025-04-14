package com.codelabs.framework_provider.interfaces.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Inject

class MockDispatcherProvider @Inject constructor() : IDispatcherProvider {
    @OptIn(ExperimentalCoroutinesApi::class)
    override val main: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val database: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()
}