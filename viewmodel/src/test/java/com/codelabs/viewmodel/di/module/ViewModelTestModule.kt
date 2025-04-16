package com.codelabs.viewmodel.di.module

import com.codelabs.logic.bloodglucose.AddBloodGlucoseReadingUseCase
import com.codelabs.logic.bloodglucose.GetAverageBloodGlucoseLevelUseCase
import com.codelabs.logic.bloodglucose.GetBloodGlucoseReadingsUseCase
import com.codelabs.viewmodel.bloodglucose.AverageReadingViewModel
import com.codelabs.viewmodel.bloodglucose.ReadingEntryViewModel
import com.codelabs.viewmodel.bloodglucose.ReadingsViewModel
import com.codelabs.viewmodel.bloodglucose.mapper.BloodGlucoseDomainToUIModelMapper
import dagger.Module
import dagger.Provides

@Module
object ViewModelTestModule {
    @Provides
    fun provideBloodGlucoseDomainToUIModelMapper(): BloodGlucoseDomainToUIModelMapper =
        BloodGlucoseDomainToUIModelMapper()

    @Provides
    fun provideReadingsViewModel(
        getBloodGlucoseReadings: GetBloodGlucoseReadingsUseCase,
        bloodGlucoseDomainToUIModelMapper: BloodGlucoseDomainToUIModelMapper
    ): ReadingsViewModel =
        ReadingsViewModel(getBloodGlucoseReadings, bloodGlucoseDomainToUIModelMapper)

    @Provides
    fun provideAverageReadingViewModel(
        getAverageBloodGlucoseLevelUseCase: GetAverageBloodGlucoseLevelUseCase
    ): AverageReadingViewModel =
        AverageReadingViewModel(getAverageBloodGlucoseLevelUseCase)

    @Provides
    fun provideReadingEntryViewModel(
        addBloodGlucoseReadingUseCase: AddBloodGlucoseReadingUseCase
    ): ReadingEntryViewModel = ReadingEntryViewModel(addBloodGlucoseReadingUseCase)
}