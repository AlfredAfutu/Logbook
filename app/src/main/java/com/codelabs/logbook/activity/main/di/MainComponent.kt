package com.codelabs.logbook.activity.main.di

import com.codelabs.logbook.activity.main.MainActivity
import com.codelabs.viewmodel.di.ViewModelModule
import com.codelabs.viewmodel.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ViewModelModule::class])
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}