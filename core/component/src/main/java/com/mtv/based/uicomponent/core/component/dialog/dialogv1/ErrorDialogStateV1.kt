package com.mtv.based.uicomponent.core.component.dialog.dialogv1

import com.mtv.based.uicomponent.core.ui.util.Constants.Companion.EMPTY_STRING
import com.mtv.based.uicomponent.core.ui.util.Constants.Companion.OK_STRING
import com.mtv.based.uicomponent.core.ui.util.Constants.Companion.WARNING_STRING

data class ErrorDialogStateV1(
    var title: String = WARNING_STRING,
    var message: String = EMPTY_STRING,
    var primaryButtonText: String = OK_STRING
)
