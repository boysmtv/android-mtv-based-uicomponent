package com.mtv.based.uicomponent.sheet.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.content.withStyledAttributes
import com.mtv.based.uicomponent.sheet.BottomSheetState
import com.mtv.based.uicomponent.sheet.R
import com.mtv.based.uicomponent.sheet.compose.AppBottomSheet
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class AppBottomSheetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var state: BottomSheetState = BottomSheetState.Hidden
    private val composeView = ComposeView(context).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.ComponentBottomSheet) {
            val title = getString(R.styleable.ComponentBottomSheet_title) ?: ""
            val message = getString(R.styleable.ComponentBottomSheet_message) ?: ""
            if (title.isNotEmpty() || message.isNotEmpty()) {
                state = BottomSheetState.Visible(title, message)
            }
        }
        addView(composeView)
        render()
    }

    private fun render() {
        composeView.setContent {
            AppTheme {
                AppBottomSheet(
                    state = state,
                    onDismiss = {
                        state = BottomSheetState.Hidden
                        render()
                    }
                )
            }
        }
    }

    fun showBottomSheet(title: String, message: String) {
        state = BottomSheetState.Visible(title, message)
        render()
    }

    fun hideBottomSheet() {
        state = BottomSheetState.Hidden
        render()
    }
}
