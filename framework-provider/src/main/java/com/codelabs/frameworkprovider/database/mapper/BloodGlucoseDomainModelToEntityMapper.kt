package com.codelabs.frameworkprovider.database.mapper

import com.codelabs.frameworkprovider.database.entity.BloodGlucoseEntity
import com.codelabs.model.BloodGlucose
import javax.inject.Inject

class BloodGlucoseDomainModelToEntityMapper @Inject constructor() {
    operator fun invoke(domain: BloodGlucose): BloodGlucoseEntity {
        return BloodGlucoseEntity(
            id = domain.id,
            timestamp = domain.timestamp,
            level = domain.level
        )
    }
}