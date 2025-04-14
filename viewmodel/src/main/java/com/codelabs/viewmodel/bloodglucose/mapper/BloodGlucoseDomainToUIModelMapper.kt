package com.codelabs.viewmodel.bloodglucose.mapper

import com.codelabs.model.BloodGlucose
import com.codelabs.model.toMGDL
import com.codelabs.viewmodel.bloodglucose.model.ReadingUIModel
import javax.inject.Inject

class BloodGlucoseDomainToUIModelMapper @Inject constructor() {
    operator fun invoke(bloodGlucoseReadings: List<BloodGlucose>): List<ReadingUIModel> =
        bloodGlucoseReadings.map { reading ->
            ReadingUIModel(
                dateString = reading.timestamp.toString(),
                levelInMgDL = reading.level.toMGDL(),
                levelInMMOLL = reading.level
            )
        }
}