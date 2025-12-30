package com.mtv.based.uicomponent.core.component.checkbox.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.mtv.based.uicomponent.core.component.checkbox.compose.AppCheckbox
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class AppCheckboxView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val composeView = ComposeView(context).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    }

    init {
        addView(composeView)
    }

    fun setChecked(checked: Boolean, onChange: (Boolean) -> Unit) {
        composeView.setContent {
            AppTheme {
                AppCheckbox(
                    checked = checked,
                    onCheckedChange = onChange,
                    label = ""
                )
            }
        }
    }
}
