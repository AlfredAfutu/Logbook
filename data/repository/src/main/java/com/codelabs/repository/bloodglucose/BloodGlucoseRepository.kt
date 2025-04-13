package com.codelabs.repository.bloodglucose

import com.codelabs.framework_provider.interfaces.database.ILogbookDatabase
import com.codelabs.model.BloodGlucose
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BloodGlucoseRepository @Inject constructor(private val database: ILogbookDatabase) {
    suspend fun addBloodGlucoseReading(bloodGlucose: BloodGlucose) {
        database.bloodGlucoseDao().insert(bloodGlucose)
    }

    fun getAllBloodGlucoseReadings(): Flow<List<BloodGlucose>> {
        return database.bloodGlucoseDao().getAll()
    }
}