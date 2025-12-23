package com.mtv.based.uicomponent.component.input.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mtv.based.uicomponent.component.input.InputState
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    state: InputState = InputState.Enabled
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = state != InputState.Disabled,
            isError = state is InputState.Error,
            label = label?.let { { Text(it) } },
            placeholder = placeholder?.let { { Text(it) } },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppTheme.colors.primary,
                unfocusedBorderColor = AppTheme.colors.outline,
                errorBorderColor = AppTheme.colors.primary
            )
        )
        if (state is InputState.Error) {
            Text(
                text = state.message,
                color = AppTheme.colors.primary,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
