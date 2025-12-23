package com.mtv.based.uicomponent.theme.ui.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = androidx.compose.material3.lightColorScheme(
            primary = Primary,
            onPrimary = OnPrimary,
            secondary = Secondary,
            surface = Surface,
            error = Error
        ),
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}