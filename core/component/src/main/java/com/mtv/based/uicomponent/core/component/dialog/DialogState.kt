package com.mtv.based.uicomponent.core.component.dialog

import com.mtv.based.uicomponent.core.ui.util.Constants.Companion.OK_STRING

sealed interface DialogState {
    object Hidden : DialogState
    data class Visible(
        val title: String,
        val message: String,
        val positiveText: String = OK_STRING,
        val negativeText: String? = null
    ) : DialogState
}
