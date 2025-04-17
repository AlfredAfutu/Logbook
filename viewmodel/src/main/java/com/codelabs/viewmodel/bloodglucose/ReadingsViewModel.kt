package com.codelabs.viewmodel.bloodglucose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.logic.bloodglucose.GetBloodGlucoseReadingsUseCase
import com.codelabs.viewmodel.bloodglucose.mapper.BloodGlucoseDomainToUIModelMapper
import com.codelabs.viewmodel.bloodglucose.model.ReadingUIModel
import com.codelabs.viewmodel.di.ActivityScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ActivityScope
class ReadingsViewModel @Inject constructor(
    getBloodGlucoseReadings: GetBloodGlucoseReadingsUseCase,
    private val mapper: BloodGlucoseDomainToUIModelMapper
) : ViewModel() {
    sealed class State {
        data object Loading : State()
        data class Success(val readings: List<ReadingUIModel>) : State()
        data object Empty : State()
    }

    val state: StateFlow<State> = getBloodGlucoseReadings()
        .map { bloodGlucoseReadings ->
            when {
                bloodGlucoseReadings.isEmpty() -> State.Empty
                else -> State.Success(mapper(bloodGlucoseReadings))
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 1000L),
            State.Loading
        )
}