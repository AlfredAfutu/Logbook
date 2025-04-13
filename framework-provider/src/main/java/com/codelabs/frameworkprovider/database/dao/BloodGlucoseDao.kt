package com.codelabs.frameworkprovider.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codelabs.framework_provider.interfaces.database.dao.IBloodGlucoseDao
import com.codelabs.frameworkprovider.database.entity.BloodGlucoseEntity
import com.codelabs.frameworkprovider.database.mapper.BloodGlucoseDomainModelToEntityMapper
import com.codelabs.frameworkprovider.database.mapper.BloodGlucoseEntityToDomainModelMapper
import com.codelabs.model.BloodGlucose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface BloodGlucoseDao : IBloodGlucoseDao {
    @Insert
    suspend fun insertBloodGlucose(bloodGlucoseEntity: BloodGlucoseEntity)

    override suspend fun insert(bloodGlucose: BloodGlucose) {
        val mapper = BloodGlucoseDomainModelToEntityMapper()
        insertBloodGlucose(mapper(bloodGlucose))
    }

    @Query("SELECT * FROM blood_glucose")
    fun getAllBloodGlucose(): Flow<List<BloodGlucoseEntity>>

    override fun getAll(): Flow<List<BloodGlucose>> {
        val mapper = BloodGlucoseEntityToDomainModelMapper()
        return getAllBloodGlucose().map { mapper(it) }
    }
}