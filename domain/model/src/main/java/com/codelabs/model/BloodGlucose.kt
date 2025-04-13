package com.codelabs.model

import java.time.LocalDateTime

data class BloodGlucose(
    val id: Long,
    val timestamp: LocalDateTime, // In milliseconds since epoch
    val level: Double // In mmol/L
)