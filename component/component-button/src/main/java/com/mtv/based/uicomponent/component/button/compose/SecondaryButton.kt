package com.mtv.based.uicomponent.component.button.compose

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
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
        border = ButtonDefaults.outlinedButtonBorder.copy(
            brush = SolidColor(AppTheme.colors.outline)
        )
    ) {
        Text(text)
    }
}
