/*
 * Project: App Movie Compose
 * Author: Boys.mtv@gmail.com
 * File: DialogStateV1.kt
 *
 * Last modified by Dedy Wijaya on 02/02/26 10.30
 */

package com.mtv.based.uicomponent.core.component.dialog.dialogv1

import com.mtv.based.uicomponent.core.ui.util.Constants.Companion.EMPTY_STRING
import com.mtv.based.uicomponent.core.ui.util.Constants.Companion.OK_STRING

data class DialogStateV1(
    val type: DialogType = DialogType.WARNING,
    val title: String = EMPTY_STRING,
    val message: String = EMPTY_STRING,
    val primaryButtonText: String = OK_STRING,
    val secondaryButtonText: String? = null
)
