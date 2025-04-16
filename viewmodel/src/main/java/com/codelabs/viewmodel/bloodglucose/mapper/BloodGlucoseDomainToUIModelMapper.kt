package com.codelabs.viewmodel.bloodglucose.mapper

import com.codelabs.model.BloodGlucose
import com.codelabs.model.toMGDL
import com.codelabs.model.toTwoDecimals
import com.codelabs.viewmodel.bloodglucose.model.ReadingUIModel
import javax.inject.Inject

class BloodGlucoseDomainToUIModelMapper @Inject constructor() {
    operator fun invoke(bloodGlucoseReadings: List<BloodGlucose>): List<ReadingUIModel> =
        bloodGlucoseReadings
            .map { reading ->
                ReadingUIModel(
                    dateString = "${reading.timestamp.dayOfMonth}-${reading.timestamp.monthValue}-${reading.timestamp.year}",
                    levelInMgDL = reading.level.toMGDL().toTwoDecimals(),
                    levelInMMOLL = reading.level.toTwoDecimals()
                )
            }
}