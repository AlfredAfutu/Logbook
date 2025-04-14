package com.codelabs.viewmodel.bloodglucose.di

import com.codelabs.logic.di.DomainModule
import com.codelabs.viewmodel.bloodglucose.mapper.BloodGlucoseDomainToUIModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module(includes = [DomainModule::class])
@InstallIn(ViewModelComponent::class)
object BloodGlucoseModule {
    @Provides
    fun provideBloodGlucoseDomainToUIModelMapper(): BloodGlucoseDomainToUIModelMapper =
        BloodGlucoseDomainToUIModelMapper()
}