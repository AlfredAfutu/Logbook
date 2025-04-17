package com.codelabs.viewmodel.bloodglucose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.logic.bloodglucose.GetAverageBloodGlucoseLevelUseCase
import com.codelabs.model.Unit
import com.codelabs.model.toTwoDecimals
import com.codelabs.viewmodel.bloodglucose.model.AverageReadingUIModel
import com.codelabs.viewmodel.di.ActivityScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ActivityScope
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
                    Unit.MMOL_L -> constructSuccessState(averageLevel.mmoLL, unit)
                    Unit.MG_DL -> constructSuccessState(averageLevel.mgDL, unit)
                }
            }
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 1000L),
            State.Loading
        )

    private fun constructSuccessState(
        averageLevel: Double,
        unit: Unit
    ): State = State.Success(AverageReadingUIModel(averageLevel.toTwoDecimals(), unit))

    fun onUnitChange(unit: Unit) {
        selectedUnit.value = unit
    }
}