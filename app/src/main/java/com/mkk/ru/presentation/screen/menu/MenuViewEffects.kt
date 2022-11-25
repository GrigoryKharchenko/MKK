package com.mkk.ru.presentation.screen.menu

sealed interface MenuViewEffects {
    data class ChangeShift(val isOpenedShift: Boolean) : MenuViewEffects
}
