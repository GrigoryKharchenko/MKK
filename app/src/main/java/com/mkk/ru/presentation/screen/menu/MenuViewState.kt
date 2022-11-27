package com.mkk.ru.presentation.screen.menu

sealed interface MenuViewState {
    data class ChangeShift(val isOpenedShift: Boolean) : MenuViewState
}
