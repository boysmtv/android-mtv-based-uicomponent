package com.mtv.based.uicomponent.component.dialog.compose

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.mtv.based.uicomponent.component.dialog.DialogState
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

@Composable
fun AppDialog(
    state: DialogState,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (state is DialogState.Visible) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(state.title, color = AppTheme.colors.onBackground) },
            text = { Text(state.message, color = AppTheme.colors.onBackground) },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(state.positiveText, color = AppTheme.colors.primary)
                }
            },
            dismissButton = state.negativeText?.let {
                {
                    TextButton(onClick = onDismiss) {
                        Text(it, color = AppTheme.colors.outline)
                    }
                }
            }
        )
    }
}
