package com.codelabs.screen.bloodglucose.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.codelabs.model.Unit
import com.codelabs.screen.R

@Composable
fun Unit.label(): String =
    when (this) {
        Unit.MMOL_L -> stringResource(R.string.mmoll_label)
        Unit.MG_DL -> stringResource(R.string.mgdl_label)
    }