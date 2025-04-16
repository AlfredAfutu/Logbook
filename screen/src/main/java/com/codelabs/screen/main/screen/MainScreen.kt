package com.codelabs.screen.main.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codelabs.screen.main.theme.LogbookTheme
import com.codelabs.viewmodel.bloodglucose.AverageReadingViewModel
import com.codelabs.viewmodel.bloodglucose.ReadingEntryViewModel
import com.codelabs.viewmodel.bloodglucose.ReadingsViewModel

@Composable
fun MainScreen(
    readingsViewModel: ReadingsViewModel,
    averageReadingViewModel: AverageReadingViewModel,
    readingEntryViewModel: ReadingEntryViewModel
) {
    LogbookTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val readingsState = readingsViewModel.state.collectAsStateWithLifecycle().value
            Greeting(
                name = if (readingsState is ReadingsViewModel.State.Empty) "No readings" else "Android",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LogbookTheme {
        Greeting("Android")
    }
}