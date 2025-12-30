package com.mtv.based.uicomponent.core.component.dialog

sealed interface DialogState {
    object Hidden : DialogState
    data class Visible(
        val title: String,
        val message: String,
        val positiveText: String = "OK",
        val negativeText: String? = null
    ) : DialogState
}
