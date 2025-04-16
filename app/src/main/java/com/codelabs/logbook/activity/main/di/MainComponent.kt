package com.codelabs.logbook.activity.main.di

import com.codelabs.logbook.activity.main.MainActivity
import com.codelabs.viewmodel.di.ViewModelModule
import com.codelabs.viewmodel.di.ViewModelScope
import dagger.Subcomponent

@ViewModelScope
@Subcomponent(modules = [ViewModelModule::class])
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}