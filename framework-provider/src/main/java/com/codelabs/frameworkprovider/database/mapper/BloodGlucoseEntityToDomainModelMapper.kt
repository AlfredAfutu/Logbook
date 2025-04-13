package com.codelabs.frameworkprovider.database.mapper

import com.codelabs.frameworkprovider.database.entity.BloodGlucoseEntity
import com.codelabs.model.BloodGlucose
import javax.inject.Inject

class BloodGlucoseEntityToDomainModelMapper @Inject constructor() {
    operator fun invoke(entities: List<BloodGlucoseEntity>): List<BloodGlucose> {
        return entities.map { entity ->
            BloodGlucose(
                id = entity.id,
                timestamp = entity.timestamp,
                level = entity.level
            )
        }
    }
}