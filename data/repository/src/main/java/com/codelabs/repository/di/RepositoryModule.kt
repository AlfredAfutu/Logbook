package com.codelabs.repository.di

import com.codelabs.framework_provider.interfaces.database.ILogbookDatabase
import com.codelabs.repository.bloodglucose.BloodGlucoseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideBloodGlucoseRepository(logbookDatabase: ILogbookDatabase): BloodGlucoseRepository {
        return BloodGlucoseRepository(logbookDatabase)
    }
}