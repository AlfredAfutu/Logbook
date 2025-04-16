package com.codelabs.screen.bloodglucose.average

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.codelabs.screen.R
import com.codelabs.screen.bloodglucose.extension.label
import com.codelabs.screen.theme.SizeDp
import com.codelabs.viewmodel.bloodglucose.AverageReadingViewModel

@Composable
fun AverageReadingComponent(state: AverageReadingViewModel.State, contentPadding: PaddingValues) {
    val text = when (state) {
        is AverageReadingViewModel.State.Loading -> stringResource(R.string.average_reading_loading_text)
        is AverageReadingViewModel.State.Empty -> stringResource(R.string.average_reading_empty_text)
        is AverageReadingViewModel.State.Success -> String.format(
            stringResource(R.string.average_reading_loaded_text),
            "${state.uiModel.level} ${state.uiModel.unit.label()}",
        )
    }
    Box(
        modifier = Modifier.padding(
            top = SizeDp.Size20 + contentPadding.calculateTopPadding(),
        )
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(SizeDp.Size8)) {
            Text(
                text = text,
                modifier = Modifier.padding(SizeDp.Size4)
            )

            HorizontalDivider(thickness = SizeDp.Size2, color = Color.DarkGray)
        }
    }
}