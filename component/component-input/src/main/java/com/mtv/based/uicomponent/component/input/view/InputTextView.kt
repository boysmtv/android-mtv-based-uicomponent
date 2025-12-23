package com.mtv.based.uicomponent.component.input.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.content.withStyledAttributes
import com.mtv.based.uicomponent.component.input.InputState
import com.mtv.based.uicomponent.component.input.R
import com.mtv.based.uicomponent.component.input.compose.AppTextField
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class InputTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val textState = mutableStateOf("")
    private val state = mutableStateOf<InputState>(InputState.Enabled)
    private var label: String? = null
    private var placeholder: String? = null
    private val composeView = ComposeView(context).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.ComponentInput) {
            label = getString(R.styleable.ComponentInput_label)
            placeholder = getString(R.styleable.ComponentInput_placeholder)

            val enabled = getBoolean(R.styleable.ComponentInput_enabled, true)
            val error = getString(R.styleable.ComponentInput_errorText)

            state.value = when {
                !error.isNullOrEmpty() -> InputState.Error(error)
                enabled -> InputState.Enabled
                else -> InputState.Disabled
            }
        }
        addView(composeView)
        render()
    }

    private fun render() {
        composeView.setContent {
            AppTheme {
                AppTextField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    label = label,
                    placeholder = placeholder,
                    state = state.value
                )
            }
        }
    }

    fun getText(): String = textState.value

    fun setError(message: String) {
        state.value = InputState.Error(message)
        render()
    }

    fun clearError() {
        state.value = InputState.Enabled
        render()
    }
}
