package com.codelabs.repository.di.module

import com.codelabs.framework_provider.interfaces.database.ILogbookDatabase
import com.codelabs.framework_provider.interfaces.dispatcher.IDispatcherProvider
import com.codelabs.repository.bloodglucose.BloodGlucoseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryTestModule {
    @Singleton
    @Provides
    fun provideBloodGlucoseRepository(
        logbookDatabase: ILogbookDatabase,
        databaseDispatcher: IDispatcherProvider
    ): BloodGlucoseRepository {
        return BloodGlucoseRepository(logbookDatabase, databaseDispatcher)
    }
}