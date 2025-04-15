package com.codelabs.framework_provider.interfaces.database.dao

import com.codelabs.model.BloodGlucose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MockBloodGlucoseDao @Inject constructor() : IBloodGlucoseDao {
    private val _bloodGlucoseReadings = mutableListOf<BloodGlucose>()
    var insertCount: Int = 0
        private set
    val levels = mutableListOf<Double>()
    val timestamps = mutableListOf<String>()
    val flow: MutableStateFlow<List<BloodGlucose>> = MutableStateFlow(emptyList())

    override suspend fun insert(bloodGlucose: BloodGlucose) {
        insertCount++
        _bloodGlucoseReadings.add(bloodGlucose)
        flow.emit(_bloodGlucoseReadings)
        levels.add(bloodGlucose.level)
        timestamps.add(bloodGlucose.timestamp.toString())
    }

    override fun getAll(): Flow<List<BloodGlucose>> = flow
}