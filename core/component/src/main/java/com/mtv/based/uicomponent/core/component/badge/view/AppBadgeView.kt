package com.mtv.based.uicomponent.core.component.badge.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.mtv.based.uicomponent.core.component.badge.compose.AppBadge
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class AppBadgeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val composeView = ComposeView(context).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    }

    init {
        addView(composeView)
    }

    fun setText(text: String) {
        composeView.setContent { AppTheme { AppBadge(text = text) } }
    }
}
