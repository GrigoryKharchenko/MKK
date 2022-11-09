package com.mkk.ru.presentation.screen.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor() : ViewModel() {

    private val _viewEffectsFlow = MutableSharedFlow<MenuViewEffects>()
    val viewEffectsFlow get() = _viewEffectsFlow.asSharedFlow()

    fun changeShift(isOpenedShift: Boolean) {
        viewModelScope.launch {
            _viewEffectsFlow.emit(MenuViewEffects.ChangeShift(isOpenedShift = isOpenedShift))
        }
    }
}
