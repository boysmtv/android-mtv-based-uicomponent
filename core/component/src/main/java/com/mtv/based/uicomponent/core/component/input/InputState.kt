package com.mtv.based.uicomponent.core.component.input

sealed interface InputState {
    object Enabled : InputState
    object Disabled : InputState
    data class Error(val message: String) : InputState
}
