package com.mtv.based.uicomponent.component.dialog.dialogv1

import com.mtv.based.uicomponent.core.ui.util.Constant.EMPTY_STRING
import com.mtv.based.uicomponent.core.ui.util.Constant.OK_STRING
import com.mtv.based.uicomponent.core.ui.util.Constant.WARNING_STRING

data class ErrorDialogStateV1(
    var title: String = WARNING_STRING,
    var message: String = EMPTY_STRING,
    var primaryButtonText: String = OK_STRING
)
