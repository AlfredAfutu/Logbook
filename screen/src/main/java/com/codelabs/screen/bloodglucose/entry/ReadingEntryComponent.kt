package com.codelabs.screen.bloodglucose.entry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.codelabs.screen.R
import com.codelabs.screen.bloodglucose.extension.label
import com.codelabs.screen.theme.SizeDp
import com.codelabs.viewmodel.bloodglucose.model.ReadingEntryUIModel
import kotlin.Unit
import com.codelabs.model.Unit as MeasurementUnit

@Composable
fun ReadingEntryComponent(
    state: ReadingEntryUIModel,
    contentPadding: PaddingValues,
    onOptionSelected: (MeasurementUnit) -> Unit,
    onLevelChanged: (String) -> Unit,
    onSaveTap: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = contentPadding.calculateTopPadding() - SizeDp.Size20)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SizeDp.Size4)
    ) {
        Text(
            text = stringResource(id = R.string.reading_entry_section_title),
            modifier = Modifier.padding(SizeDp.Size4)
        )

        UnitSelectionComponent(
            options = state.unitOptions,
            selectedOption = state.selectedUnit,
            onOptionSelected = onOptionSelected
        )

        MeasurementInputComponent(
            level = state.level,
            selectedUnit = state.selectedUnit,
            onLevelChanged = onLevelChanged
        )

        SaveButtonComponent(isEnabled = state.isInvalid.not(), onSaveClicked = onSaveTap)
    }
}

@Composable
private fun UnitSelectionComponent(
    options: List<MeasurementUnit>,
    selectedOption: MeasurementUnit,
    onOptionSelected: (MeasurementUnit) -> Unit
) {
    val latestSelectedOption = remember { mutableStateOf(selectedOption) }

    Column(
        verticalArrangement = Arrangement.spacedBy(SizeDp.Size4)
    ) {
        options.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = latestSelectedOption.value == option,
                    onClick = {
                        latestSelectedOption.value = option
                        onOptionSelected(option)
                    }
                )
                Text(
                    text = option.label(),
                    modifier = Modifier.padding(start = SizeDp.Size4)
                )
            }
        }
    }
}

@Composable
private fun MeasurementInputComponent(
    level: String,
    onLevelChanged: (String) -> Unit,
    selectedUnit: MeasurementUnit
) {
    OutlinedTextField(
        value = level,
        onValueChange = { onLevelChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
        suffix = { Text(text = selectedUnit.label()) }
    )
}

@Composable
fun ColumnScope.SaveButtonComponent(isEnabled: Boolean, onSaveClicked: () -> Unit) {
    Button(
        onClick = onSaveClicked,
        modifier = Modifier.align(Alignment.End),
        enabled = isEnabled
    ) {
        Text(text = stringResource(R.string.save_button_text))
    }
}
