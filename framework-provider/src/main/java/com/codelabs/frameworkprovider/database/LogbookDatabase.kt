package com.codelabs.frameworkprovider.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codelabs.framework_provider.interfaces.database.ILogbookDatabase
import com.codelabs.frameworkprovider.database.converter.LocalDateTimeConverter
import com.codelabs.frameworkprovider.database.dao.BloodGlucoseDao
import com.codelabs.frameworkprovider.database.entity.BloodGlucoseEntity

@Database(entities = [BloodGlucoseEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class LogbookDatabase : RoomDatabase(), ILogbookDatabase {
    abstract override fun bloodGlucoseDao(): BloodGlucoseDao
}