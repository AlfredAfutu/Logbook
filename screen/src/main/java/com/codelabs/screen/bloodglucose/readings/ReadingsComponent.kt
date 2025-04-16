package com.codelabs.screen.bloodglucose.readings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codelabs.model.Unit
import com.codelabs.screen.R
import com.codelabs.screen.bloodglucose.extension.label
import com.codelabs.screen.theme.SizeDp
import com.codelabs.viewmodel.bloodglucose.ReadingsViewModel
import com.codelabs.viewmodel.bloodglucose.model.ReadingUIModel

@Composable
fun ReadingsComponent(state: ReadingsViewModel.State) {
    Column(verticalArrangement = Arrangement.spacedBy(SizeDp.Size8)) {
        Text(
            text = stringResource(R.string.previous_readings_section_title),
            style = MaterialTheme.typography.titleLarge,
        )

        HorizontalDivider(thickness = SizeDp.Size2, color = Color.DarkGray)

        Spacer(modifier = Modifier.size(SizeDp.Size8))

        when (state) {
            is ReadingsViewModel.State.Loading -> ReadingsLoadingComponent()
            is ReadingsViewModel.State.Empty -> ReadingsEmptyComponent()
            is ReadingsViewModel.State.Success -> ReadingsLoadedComponent(state.readings)
        }
    }
}

@Composable
private fun ReadingsLoadingComponent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SizeDp.Size8),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ReadingsEmptyComponent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SizeDp.Size8),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = stringResource(R.string.previous_readings_section_empty_text),
                modifier = Modifier.size(SizeDp.Size48)
            )
            Text(
                text = stringResource(R.string.previous_readings_section_empty_text),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun ReadingsLoadedComponent(readings: List<ReadingUIModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(readings) { reading ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = SizeDp.Size4)
            ) {
                Text(text = "Reading: ${reading.levelInMMOLL} ${Unit.MMOL_L.label()} (${reading.levelInMgDL} ${Unit.MG_DL.label()})")
                Text(text = "Date: ${reading.dateString}")
            }
            if (readings.last() != reading) HorizontalDivider(thickness = 0.5.dp)
        }
    }
}