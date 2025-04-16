package com.codelabs.repository.di.component

import com.codelabs.framework_provider.interfaces.di.FrameworkProviderMockModule
import com.codelabs.repository.bloodglucose.BloodGlucoseRepositoryTest
import com.codelabs.repository.di.module.RepositoryTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FrameworkProviderMockModule::class, RepositoryTestModule::class])
interface RepositoryTestComponent {
    fun inject(test: BloodGlucoseRepositoryTest)
}