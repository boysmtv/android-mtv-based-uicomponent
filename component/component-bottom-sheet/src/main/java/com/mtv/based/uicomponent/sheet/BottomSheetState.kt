package com.mtv.based.uicomponent.sheet

sealed interface BottomSheetState {
    object Hidden : BottomSheetState
    data class Visible(val title: String, val message: String) : BottomSheetState
}
