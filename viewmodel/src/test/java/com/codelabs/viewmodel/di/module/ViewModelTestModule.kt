package com.codelabs.viewmodel.di.module

import com.codelabs.logic.bloodglucose.GetBloodGlucoseReadingsUseCase
import com.codelabs.viewmodel.bloodglucose.ReadingsViewModel
import com.codelabs.viewmodel.bloodglucose.mapper.BloodGlucoseDomainToUIModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
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
}