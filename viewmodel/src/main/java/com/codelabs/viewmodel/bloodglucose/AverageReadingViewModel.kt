package com.codelabs.viewmodel.bloodglucose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.logic.bloodglucose.GetAverageBloodGlucoseLevelUseCase
import com.codelabs.model.Unit
import com.codelabs.viewmodel.bloodglucose.model.AverageReadingUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AverageReadingViewModel @Inject constructor(
    getAverageBloodGlucoseLevel: GetAverageBloodGlucoseLevelUseCase
) : ViewModel() {
    sealed class State {
        data object Loading : State()
        data class Success(val uiModel: AverageReadingUIModel) : State()
        data object Empty : State()
    }

    private val selectedUnit: MutableStateFlow<Unit> = MutableStateFlow(Unit.MMOL_L)

    val state = getAverageBloodGlucoseLevel().combine(selectedUnit) { averageLevel, unit ->
        when {
            averageLevel == null -> State.Empty
            else -> {
                when (unit) {
                    Unit.MMOL_L -> State.Success(AverageReadingUIModel(averageLevel.mmoLL, unit))
                    Unit.MG_DL -> State.Success(AverageReadingUIModel(averageLevel.mgDL, unit))
                }
            }
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 1000L),
            State.Loading
        )

    fun onUnitChange(unit: Unit) {
        selectedUnit.value = unit
    }
}