package com.mtv.based.uicomponent.core.component.button

sealed interface ButtonState {
    object Enabled : ButtonState
    object Disabled : ButtonState
    object Loading : ButtonState
}