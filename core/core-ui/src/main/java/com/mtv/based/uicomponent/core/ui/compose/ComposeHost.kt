package com.mtv.based.uicomponent.core.ui.compose

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

abstract class ComposeHost : Fragment() {

    abstract fun Content()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val composeView = view as ComposeView
        composeView.setContent {
            AppTheme {
                Content()
            }
        }
    }
}
