package com.codelabs.model

import java.time.LocalDateTime

data class ReadingInput(val level: Double, val unit: Unit, val timestamp: LocalDateTime)

enum class Unit {
    MMOL_L,
    MG_DL;
}

fun Double.toMMOLL(): Double = this / 18.0182
fun Double.toMGDL(): Double = this * 18.0182