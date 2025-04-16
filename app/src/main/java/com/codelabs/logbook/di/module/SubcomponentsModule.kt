package com.codelabs.logbook.di.module

import com.codelabs.logbook.activity.main.di.MainComponent
import dagger.Module

@Module(subcomponents = [MainComponent::class])
class SubcomponentsModule