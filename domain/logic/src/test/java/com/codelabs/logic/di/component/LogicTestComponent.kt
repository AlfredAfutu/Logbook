package com.codelabs.logic.di.component

import com.codelabs.framework_provider.interfaces.di.FrameworkProviderMockModule
import com.codelabs.logic.bloodglucose.AddBloodGlucoseReadingInputUseCaseTest
import com.codelabs.logic.di.DomainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FrameworkProviderMockModule::class, DomainModule::class])
interface LogicTestComponent {
    fun inject(test: AddBloodGlucoseReadingInputUseCaseTest)
}