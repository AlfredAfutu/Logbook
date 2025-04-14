package com.codelabs.framework_provider.interfaces.database.dao

import com.codelabs.model.BloodGlucose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MockBloodGlucoseDao @Inject constructor() : IBloodGlucoseDao {
    private val bloodGlucoseReadings = mutableListOf<BloodGlucose>()
    var insertCount: Int = 0
        private set

    override suspend fun insert(bloodGlucose: BloodGlucose) {
        insertCount++
        bloodGlucoseReadings.add(bloodGlucose)
    }

    override fun getAll(): Flow<List<BloodGlucose>> {
        return flowOf(bloodGlucoseReadings)
    }
}