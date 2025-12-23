package com.mtv.based.uicomponent.theme.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColors = AppColors(
    primary = Color(0xFF0061A8),
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    outline = Color(0xFFDDDDDD)
)

private val LocalColors = staticCompositionLocalOf { LightColors }
private val LocalDimens = staticCompositionLocalOf { AppDimens() }

object AppTheme {
    val colors: AppColors
        @Composable get() = LocalColors.current

    val dimens: AppDimens
        @Composable get() = LocalDimens.current
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    androidx.compose.runtime.CompositionLocalProvider(
        LocalColors provides LightColors,
        LocalDimens provides AppDimens(),
        content = content
    )
}
