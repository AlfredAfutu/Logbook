package com.codelabs.framework_provider.interfaces.database.dao

import com.codelabs.model.BloodGlucose
import kotlinx.coroutines.flow.Flow

interface IBloodGlucoseDao {
    suspend fun insert(bloodGlucose: BloodGlucose)
    fun getAll(): Flow<List<BloodGlucose>>
}