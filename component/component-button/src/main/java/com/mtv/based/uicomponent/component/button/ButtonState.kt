package com.mtv.based.uicomponent.component.button

sealed interface ButtonState {
    object Enabled : ButtonState
    object Disabled : ButtonState
    object Loading : ButtonState
}