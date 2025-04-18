package com.codelabs.viewmodel.bloodglucose

import androidx.lifecycle.ViewModel
import com.codelabs.logic.bloodglucose.AddBloodGlucoseReadingUseCase
import com.codelabs.model.ReadingInput
import com.codelabs.model.Unit
import com.codelabs.model.toMGDL
import com.codelabs.model.toMMOLL
import com.codelabs.viewmodel.bloodglucose.model.ReadingEntryUIModel
import com.codelabs.viewmodel.di.ActivityScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import javax.inject.Inject

@ActivityScope
class ReadingEntryViewModel @Inject constructor(
    private val addBloodGlucoseReading: AddBloodGlucoseReadingUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ReadingEntryUIModel.DEFAULT)
    val state: StateFlow<ReadingEntryUIModel> = _state.asStateFlow()

    fun onUnitChange(unit: Unit) {
        val newLevel: String = when (unit) {
            Unit.MMOL_L -> _state.value.level.toDoubleOrNull()?.toMMOLL()?.toString().orEmpty()
            Unit.MG_DL -> _state.value.level.toDoubleOrNull()?.toMGDL()?.toString().orEmpty()
        }
        _state.value = _state.value.copy(level = newLevel, selectedUnit = unit)
    }

    fun onLevelChange(level: String) {
        val levelForUpdate = level.toDoubleOrNull()
        val isInvalid = when {
            levelForUpdate == null || levelForUpdate < 0 -> true
            else -> false
        }
        _state.value = state.value.copy(level = level, isInvalid = isInvalid)
    }

    suspend fun onSaveTap(level: String) {
        val levelToSave = level.toDoubleOrNull()
        val newState = when {
            levelToSave == null || levelToSave < 0 -> _state.value.copy(isInvalid = true)
            else -> {
                val input = ReadingInput(
                    level = levelToSave,
                    timestamp = LocalDateTime.now(),
                    unit = state.value.selectedUnit
                )
                addBloodGlucoseReading(input).let {
                    _state.value.copy(level = "", isInvalid = true)
                }
            }
        }
        _state.value = newState
    }
}