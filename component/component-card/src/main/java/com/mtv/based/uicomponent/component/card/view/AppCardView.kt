package com.mtv.based.uicomponent.component.card.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.mtv.based.uicomponent.component.card.compose.AppCard
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class AppCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val composeView = ComposeView(context).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    }

    init {
        addView(composeView)
    }

    fun setContent(title: String, content: @Composable () -> Unit) {
        composeView.setContent { AppTheme { AppCard(title = title, content = content) } }
    }
}
