package com.mtv.based.uicomponent.core.component.badge.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

@Composable
fun AppBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.Surface(
        modifier = modifier,
        color = AppTheme.colors.primary,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            color = AppTheme.colors.onPrimary,
            modifier = Modifier.padding(4.dp, 2.dp)
        )
    }
}
