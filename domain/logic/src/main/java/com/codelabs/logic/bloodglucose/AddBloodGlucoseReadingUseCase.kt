package com.codelabs.logic.bloodglucose

import com.codelabs.model.BloodGlucose
import com.codelabs.model.ReadingInput
import com.codelabs.model.Unit
import com.codelabs.model.toMMOLL
import com.codelabs.repository.bloodglucose.BloodGlucoseRepository
import javax.inject.Inject

class AddBloodGlucoseReadingUseCase @Inject constructor(
    private val repository: BloodGlucoseRepository
) {
    suspend operator fun invoke(reading: ReadingInput) {
        val levelInMMOLL: Double = when (reading.unit) {
            Unit.MG_DL -> reading.level.toMMOLL()
            Unit.MMOL_L -> reading.level
        }
        val bloodGlucose = BloodGlucose(
            timestamp = reading.timestamp,
            level = levelInMMOLL
        )
        repository.addBloodGlucoseReading(bloodGlucose)
    }
}