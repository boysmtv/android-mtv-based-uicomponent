package com.mtv.based.uicomponent.component.button.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.component.button.ButtonState
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

@Composable
fun SecondaryButton(
    text: String,
    modifier: Modifier = Modifier,
    state: ButtonState = ButtonState.Enabled,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        enabled = state == ButtonState.Enabled,
        onClick = onClick,
        border = BorderStroke(
            width = 1.dp,
            brush = SolidColor(AppTheme.colors.outline),
        )
    ) {
        Text(text)
    }
}
