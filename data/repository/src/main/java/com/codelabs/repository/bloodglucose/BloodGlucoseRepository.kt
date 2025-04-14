package com.codelabs.repository.bloodglucose

import com.codelabs.framework_provider.interfaces.database.ILogbookDatabase
import com.codelabs.framework_provider.interfaces.dispatcher.IDispatcherProvider
import com.codelabs.model.BloodGlucose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BloodGlucoseRepository @Inject constructor(
    private val database: ILogbookDatabase,
    private val databaseDispatcher: IDispatcherProvider
) {
    suspend fun addBloodGlucoseReading(bloodGlucose: BloodGlucose) {
        withContext(databaseDispatcher.database) {
            database.bloodGlucoseDao().insert(bloodGlucose)
        }
    }

    fun getAllBloodGlucoseReadings(): Flow<List<BloodGlucose>> =
        database.bloodGlucoseDao().getAll()
            .flowOn(databaseDispatcher.database)
}