package com.codelabs.logic.di.module

import com.codelabs.logic.bloodglucose.AddBloodGlucoseReadingUseCase
import com.codelabs.logic.bloodglucose.GetAverageBloodGlucoseLevelUseCase
import com.codelabs.logic.bloodglucose.GetBloodGlucoseReadingsUseCase
import com.codelabs.repository.bloodglucose.BloodGlucoseRepository
import dagger.Module
import dagger.Provides

@Module
object DomainTestModule {
    @Provides
    fun provideAddBloodGlucoseReadingUseCase(
        bloodGlucoseRepository: BloodGlucoseRepository
    ): AddBloodGlucoseReadingUseCase = AddBloodGlucoseReadingUseCase(bloodGlucoseRepository)

    @Provides
    fun provideGetBloodGlucoseReadingsUseCase(
        bloodGlucoseRepository: BloodGlucoseRepository
    ): GetBloodGlucoseReadingsUseCase = GetBloodGlucoseReadingsUseCase(bloodGlucoseRepository)

    @Provides
    fun provideGetAverageBloodGlucoseLevelUseCase(
        bloodGlucoseRepository: BloodGlucoseRepository
    ): GetAverageBloodGlucoseLevelUseCase =
        GetAverageBloodGlucoseLevelUseCase(bloodGlucoseRepository)
}