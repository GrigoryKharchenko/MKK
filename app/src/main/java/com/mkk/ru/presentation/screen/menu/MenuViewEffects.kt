package com.mkk.ru.presentation.screen.menu

sealed class MenuViewEffects {
    data class ChangeShift(
        val isOpenedShift: Boolean
    ) : MenuViewEffects()
}
