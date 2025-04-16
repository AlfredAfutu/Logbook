package com.codelabs.screen.bloodglucose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codelabs.screen.bloodglucose.average.AverageReadingComponent
import com.codelabs.screen.bloodglucose.entry.ReadingEntryComponent
import com.codelabs.screen.bloodglucose.readings.ReadingsComponent
import com.codelabs.screen.theme.LogbookTheme
import com.codelabs.screen.theme.SizeDp
import com.codelabs.viewmodel.bloodglucose.AverageReadingViewModel
import com.codelabs.viewmodel.bloodglucose.ReadingEntryViewModel
import com.codelabs.viewmodel.bloodglucose.ReadingsViewModel
import kotlinx.coroutines.launch

@Composable
fun BloodGlucoseScreen(
    readingsViewModel: ReadingsViewModel,
    averageReadingViewModel: AverageReadingViewModel,
    readingEntryViewModel: ReadingEntryViewModel
) {
    LogbookTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val readingsState = readingsViewModel.state.collectAsStateWithLifecycle().value
            val averageReadingState =
                averageReadingViewModel.state.collectAsStateWithLifecycle().value
            val readingEntryState = readingEntryViewModel.state.collectAsStateWithLifecycle().value
            val coroutineScope = rememberCoroutineScope()

            Column(
                verticalArrangement = Arrangement.spacedBy(SizeDp.Size12),
                modifier = Modifier.padding(SizeDp.Size20)
            ) {
                AverageReadingComponent(
                    state = averageReadingState,
                    contentPadding = innerPadding
                )
                ReadingEntryComponent(
                    state = readingEntryState,
                    contentPadding = innerPadding,
                    onOptionSelected = {
                        readingEntryViewModel.onUnitChange(it)
                        averageReadingViewModel.onUnitChange(it)
                    },
                    onLevelChanged = {
                        readingEntryViewModel.onLevelChange(it)
                    },
                    onSaveTap = {
                        coroutineScope.launch {
                            readingEntryViewModel.onSaveTap(readingEntryState.level)
                        }
                    }
                )
                ReadingsComponent(state = readingsState)
            }
        }
    }
}
