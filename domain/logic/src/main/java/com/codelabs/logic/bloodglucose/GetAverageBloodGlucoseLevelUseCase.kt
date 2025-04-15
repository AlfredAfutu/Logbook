package com.codelabs.logic.bloodglucose

import com.codelabs.model.AverageLevel
import com.codelabs.model.toMGDL
import com.codelabs.repository.bloodglucose.BloodGlucoseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAverageBloodGlucoseLevelUseCase @Inject constructor(
    private val repository: BloodGlucoseRepository
) {
    operator fun invoke(): Flow<AverageLevel> =
        repository.getAllBloodGlucoseReadings()
            .map { it.map { reading -> reading.level } }
            .map { it.average() }
            .map { AverageLevel(it, it.toMGDL()) }
}