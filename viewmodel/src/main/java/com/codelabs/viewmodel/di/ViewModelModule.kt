package com.codelabs.viewmodel.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codelabs.viewmodel.bloodglucose.AverageReadingViewModel
import com.codelabs.viewmodel.bloodglucose.ReadingEntryViewModel
import com.codelabs.viewmodel.bloodglucose.ReadingsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface ViewModelModule {
    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(ReadingEntryViewModel::class)
    fun bindReadingEntryViewModel(viewModel: ReadingEntryViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(AverageReadingViewModel::class)
    fun bindAverageReadingViewModel(viewModel: AverageReadingViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(ReadingsViewModel::class)
    fun bindReadingsViewModel(viewModel: ReadingsViewModel): ViewModel

    @ActivityScope
    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)