package com.codelabs.logbook.di.component

import android.app.Application
import com.codelabs.frameworkprovider.di.FrameworkProviderModule
import com.codelabs.logbook.di.module.AppModule
import com.codelabs.logbook.di.module.SubcomponentsModule
import com.codelabs.logbook.activity.main.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, FrameworkProviderModule::class, SubcomponentsModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

    fun mainComponent(): MainComponent.Factory
}