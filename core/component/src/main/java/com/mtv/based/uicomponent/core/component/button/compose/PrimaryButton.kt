package com.mtv.based.uicomponent.core.component.button.compose

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mtv.based.uicomponent.core.component.button.ButtonState
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    state: ButtonState = ButtonState.Enabled,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = state == ButtonState.Enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary
        )
    ) {
        when (state) {
            ButtonState.Loading -> {
                CircularProgressIndicator(
                    strokeWidth = AppTheme.dimens.xs,
                    color = AppTheme.colors.onPrimary
                )
            }
            else -> Text(text)
        }
    }
}
