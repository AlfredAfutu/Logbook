package com.codelabs.logic.bloodglucose

import com.codelabs.model.BloodGlucose
import com.codelabs.repository.bloodglucose.BloodGlucoseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBloodGlucoseReadingsUseCase @Inject constructor(
    private val repository: BloodGlucoseRepository
) {
    operator fun invoke(): Flow<List<BloodGlucose>> =
        repository.getAllBloodGlucoseReadings()
            .map { it.sortedByDescending { bloodGlucose -> bloodGlucose.timestamp } }
}