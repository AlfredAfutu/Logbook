package com.codelabs.frameworkprovider.di

import android.app.Application
import androidx.room.Room
import com.codelabs.framework_provider.interfaces.database.ILogbookDatabase
import com.codelabs.frameworkprovider.database.LogbookDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FrameworkProviderModule {
    @Singleton
    @Binds
    fun bindLogbookDatabase(logbookDatabase: LogbookDatabase): ILogbookDatabase

    companion object {
        @Singleton
        @Provides
        fun provideLogbookDatabase(context: Application): LogbookDatabase {
            return Room.databaseBuilder(
                context,
                LogbookDatabase::class.java, "logbook_database"
            )
                .build()
        }
    }
}