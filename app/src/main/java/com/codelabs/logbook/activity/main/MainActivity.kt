package com.codelabs.logbook.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.codelabs.logbook.application.LogbookApplication
import com.codelabs.screen.main.screen.MainScreen
import com.codelabs.viewmodel.bloodglucose.AverageReadingViewModel
import com.codelabs.viewmodel.bloodglucose.ReadingEntryViewModel
import com.codelabs.viewmodel.bloodglucose.ReadingsViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var readingsViewModel: ReadingsViewModel
    private lateinit var averageReadingViewModel: AverageReadingViewModel
    private lateinit var readingEntryViewModel: ReadingEntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LogbookApplication).applicationComponent.mainComponent().create()
            .inject(this)
        readingsViewModel = viewModelFactory.create(ReadingsViewModel::class.java)
        averageReadingViewModel = viewModelFactory.create(AverageReadingViewModel::class.java)
        readingEntryViewModel = viewModelFactory.create(ReadingEntryViewModel::class.java)

        enableEdgeToEdge()
        setContent {
            MainScreen(readingsViewModel, averageReadingViewModel, readingEntryViewModel)
        }
    }
}
