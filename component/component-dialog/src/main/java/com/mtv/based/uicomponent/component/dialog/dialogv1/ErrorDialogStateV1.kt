package com.mtv.based.uicomponent.component.dialog.dialogv1

import com.mtv.based.uicomponent.core.ui.util.Constant.EMPTY_STRING

data class ErrorDialogStateV1(
    var title: String = EMPTY_STRING,
    var message: String = EMPTY_STRING,
    var primaryButtonText: String = EMPTY_STRING
)
