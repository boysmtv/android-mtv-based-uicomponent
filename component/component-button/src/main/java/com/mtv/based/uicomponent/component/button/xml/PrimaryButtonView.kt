package com.mtv.based.uicomponent.component.button.xml

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.content.withStyledAttributes
import com.mtv.based.uicomponent.component.button.ButtonState
import com.mtv.based.uicomponent.component.button.R
import com.mtv.based.uicomponent.component.button.compose.PrimaryButton
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class PrimaryButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var text: String = ""
    private var state: ButtonState = ButtonState.Enabled
    private var onClick: (() -> Unit)? = null

    private val composeView = ComposeView(context).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.ComponentButton) {
            text = getString(R.styleable.ComponentButton_text).orEmpty()

            val enabled = getBoolean(R.styleable.ComponentButton_enabled, true)
            val loading = getBoolean(R.styleable.ComponentButton_loading, false)

            state = when {
                loading -> ButtonState.Loading
                enabled -> ButtonState.Enabled
                else -> ButtonState.Disabled
            }
        }

        addView(composeView)
        render()
    }

    private fun render() {
        composeView.setContent {
            AppTheme {
                PrimaryButton(
                    text = text,
                    state = state,
                    onClick = { onClick?.invoke() }
                )
            }
        }
    }

    fun setOnButtonClick(listener: () -> Unit) {
        onClick = listener
    }
}