package com.mtv.based.uicomponent.sheet.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.sheet.BottomSheetState
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    state: BottomSheetState,
    onDismiss: () -> Unit
) {
    if (state is BottomSheetState.Visible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = state.title, style = MaterialTheme.typography.titleMedium, color = AppTheme.colors.onBackground)
                Text(text = state.message, style = MaterialTheme.typography.bodyMedium, color = AppTheme.colors.onBackground)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            }
        }
    }
}
