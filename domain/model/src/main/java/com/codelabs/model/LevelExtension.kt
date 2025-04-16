package com.codelabs.model

import java.util.Locale

fun Double.toMMOLL(): Double = this / 18.0182
fun Double.toMGDL(): Double = this * 18.0182

fun Double.toTwoDecimals() = String.format(Locale.ROOT, "%.2f", this).toDouble()