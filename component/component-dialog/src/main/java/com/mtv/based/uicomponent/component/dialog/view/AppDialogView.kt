package com.mtv.based.uicomponent.component.dialog.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.content.withStyledAttributes
import com.mtv.based.uicomponent.component.dialog.DialogState
import com.mtv.based.uicomponent.component.dialog.R
import com.mtv.based.uicomponent.component.dialog.compose.AppDialog
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class AppDialogView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var state: DialogState = DialogState.Hidden
    private var composeView: ComposeView = ComposeView(context).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.ComponentDialog) {
            val title = getString(R.styleable.ComponentDialog_title) ?: ""
            val message = getString(R.styleable.ComponentDialog_message) ?: ""
            val positive = getString(R.styleable.ComponentDialog_positiveText) ?: "OK"
            val negative = getString(R.styleable.ComponentDialog_negativeText)

            state = if (title.isNotEmpty() || message.isNotEmpty()) {
                DialogState.Visible(title, message, positive, negative)
            } else DialogState.Hidden
        }

        addView(composeView)
        render()
    }

    private fun render() {
        composeView.setContent {
            AppTheme {
                AppDialog(
                    state = state,
                    onDismiss = { state = DialogState.Hidden; render() },
                    onConfirm = { state = DialogState.Hidden; render() }
                )
            }
        }
    }

    fun showDialog(title: String, message: String, positive: String = "OK", negative: String? = null) {
        state = DialogState.Visible(title, message, positive, negative)
        render()
    }

    fun hideDialog() {
        state = DialogState.Hidden
        render()
    }
}
