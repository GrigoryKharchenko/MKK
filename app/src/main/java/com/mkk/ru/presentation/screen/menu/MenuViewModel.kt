package com.mkk.ru.presentation.screen.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkk.ru.domain.preference.PreferenceManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _viewEffectsFlow = MutableSharedFlow<MenuViewState>(replay = 1)
    val viewEffectsFlow get() = _viewEffectsFlow.asSharedFlow()

    init {
        getState()
    }

    fun setState(isOpenedShift: Boolean) {
        viewModelScope.launch {
            _viewEffectsFlow.emit(MenuViewState.ChangeShift(isOpenedShift))
            preferenceManager.saveStateShift(isOpenedShift)
        }
    }

    private fun getState() {
        viewModelScope.launch {
            _viewEffectsFlow.emit(MenuViewState.ChangeShift(preferenceManager.getStateShift()))
        }
    }
}
