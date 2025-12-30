package com.mtv.based.uicomponent.core.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment(
    @LayoutRes layoutId: Int
) : DialogFragment(layoutId) {

    open fun onDialogReady(view: View, savedInstanceState: Bundle?) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onDialogReady(view, savedInstanceState)
    }
}