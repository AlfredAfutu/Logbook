package com.codelabs.frameworkprovider.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "blood_glucose")
data class BloodGlucoseEntity(
    @PrimaryKey val id: Long = 0,
    val timestamp: LocalDateTime,
    val level: Double // In mmol/L
)