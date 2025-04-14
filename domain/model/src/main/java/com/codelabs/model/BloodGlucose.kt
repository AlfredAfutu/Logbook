package com.codelabs.model

import java.time.LocalDateTime

data class BloodGlucose(
    val timestamp: LocalDateTime,
    val level: Double,
    val unit: Unit = Unit.MMOL_L
)