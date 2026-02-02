/*
 * Project: App Movie Compose
 * Author: Boys.mtv@gmail.com
 * File: DialogColorV1.kt
 *
 * Last modified by Dedy Wijaya on 02/02/26 10.31
 */

package com.mtv.based.uicomponent.core.component.dialog.dialogv1

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun dialogIcon(type: DialogType): ImageVector =
    when (type) {
        DialogType.SUCCESS -> Icons.Default.CheckCircle
        DialogType.ERROR -> Icons.Default.Close
        DialogType.WARNING -> Icons.Default.Warning
    }

@Composable
fun dialogColor(type: DialogType): Color =
    when (type) {
        DialogType.SUCCESS -> Color(0xFF2E7D32)
        DialogType.ERROR -> MaterialTheme.colorScheme.error
        DialogType.WARNING -> Color(0xFFF9A825)
    }
