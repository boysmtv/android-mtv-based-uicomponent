package com.mtv.based.uicomponent.core.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet(
    @LayoutRes layoutId: Int
) : BottomSheetDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSheetReady(view, savedInstanceState)
    }

    open fun onSheetReady(view: View, savedInstanceState: Bundle?) {}
}