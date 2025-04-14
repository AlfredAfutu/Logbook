package com.codelabs.logic.di

import com.codelabs.logic.bloodglucose.AddBloodGlucoseReadingUseCase
import com.codelabs.logic.bloodglucose.GetBloodGlucoseReadingsUseCase
import com.codelabs.repository.bloodglucose.BloodGlucoseRepository
import com.codelabs.repository.di.RepositoryModule
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
object DomainModule {
    @Provides
    fun provideAddBloodGlucoseReadingUseCase(
        bloodGlucoseRepository: BloodGlucoseRepository
    ): AddBloodGlucoseReadingUseCase = AddBloodGlucoseReadingUseCase(bloodGlucoseRepository)

    @Provides
    fun provideGetBloodGlucoseReadingsUseCase(
        bloodGlucoseRepository: BloodGlucoseRepository
    ): GetBloodGlucoseReadingsUseCase = GetBloodGlucoseReadingsUseCase(bloodGlucoseRepository)
}