package com.codelabs.frameworkprovider.di

import android.app.Application
import androidx.room.Room
import com.codelabs.framework_provider.interfaces.database.ILogbookDatabase
import com.codelabs.framework_provider.interfaces.dispatcher.IDispatcherProvider
import com.codelabs.frameworkprovider.database.LogbookDatabase
import com.codelabs.frameworkprovider.dispatcher.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface FrameworkProviderModule {
    @Singleton
    @Binds
    fun bindLogbookDatabase(logbookDatabase: LogbookDatabase): ILogbookDatabase

    @Singleton
    @Binds
    fun bindDispatcherProvider(dispatcherProvider: DispatcherProvider): IDispatcherProvider

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