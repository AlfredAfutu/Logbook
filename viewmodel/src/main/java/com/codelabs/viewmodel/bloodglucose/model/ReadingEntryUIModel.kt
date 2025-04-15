package com.codelabs.viewmodel.bloodglucose.model

import com.codelabs.model.Unit

data class ReadingEntryUIModel(
    val unitOptions: List<Unit>,
    val selectedUnit: Unit,
    val level: String,
    val isInvalid: Boolean,
) {
    companion object {
        val DEFAULT = ReadingEntryUIModel(
            unitOptions = Unit.entries,
            selectedUnit = Unit.MMOL_L,
            level = "",
            isInvalid = false
        )
    }
}

