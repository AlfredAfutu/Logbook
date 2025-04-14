package com.codelabs.frameworkprovider.dispatcher

import com.codelabs.framework_provider.interfaces.dispatcher.IDispatcherProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProvider @Inject constructor(): IDispatcherProvider {
    override val main = Dispatchers.Main
    override val database = Dispatchers.IO
}