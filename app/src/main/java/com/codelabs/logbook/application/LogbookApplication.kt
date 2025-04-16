package com.codelabs.logbook.application

import android.app.Application
import com.codelabs.logbook.di.component.ApplicationComponent
import com.codelabs.logbook.di.component.DaggerApplicationComponent

class LogbookApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}