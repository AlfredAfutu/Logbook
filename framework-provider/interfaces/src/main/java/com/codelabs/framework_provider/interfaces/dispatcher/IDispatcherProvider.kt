package com.codelabs.framework_provider.interfaces.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {
    val main: CoroutineDispatcher
    val database: CoroutineDispatcher
}