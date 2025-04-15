package com.codelabs.viewmodel.di.component

import com.codelabs.framework_provider.interfaces.di.FrameworkProviderMockModule
import com.codelabs.viewmodel.bloodglucose.AverageReadingViewModelTest
import com.codelabs.viewmodel.bloodglucose.ReadingEntryViewModelTest
import com.codelabs.viewmodel.bloodglucose.ReadingsViewModelTest
import com.codelabs.viewmodel.di.module.ViewModelTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FrameworkProviderMockModule::class, ViewModelTestModule::class])
interface ViewModelTestComponent {
    fun inject(test: ReadingsViewModelTest)
    fun inject(test: AverageReadingViewModelTest)
    fun inject(test: ReadingEntryViewModelTest)
}