package com.codelabs.framework_provider.interfaces.di

import com.codelabs.framework_provider.interfaces.database.ILogbookDatabase
import com.codelabs.framework_provider.interfaces.database.MockLogbookDatabase
import com.codelabs.framework_provider.interfaces.database.dao.IBloodGlucoseDao
import com.codelabs.framework_provider.interfaces.database.dao.MockBloodGlucoseDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface FrameworkProviderMockModule {
    @Singleton
    @Binds
    fun bindLogbookDatabase(database: MockLogbookDatabase): ILogbookDatabase

    @Singleton
    @Binds
    fun bindBloodGlucoseDao(dao: MockBloodGlucoseDao): IBloodGlucoseDao

    companion object {
        @Singleton
        @Provides
        fun provideMockBloodGlucoseDao(): MockBloodGlucoseDao {
            return MockBloodGlucoseDao()
        }

        @Singleton
        @Provides
        fun provideLogbookDatabase(dao: IBloodGlucoseDao): MockLogbookDatabase {
            return MockLogbookDatabase(dao)
        }
    }
}