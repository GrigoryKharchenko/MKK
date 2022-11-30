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

    private val _stateShiftFlow = MutableSharedFlow<Boolean>(replay = 1)
    val stateShiftFlow get() = _stateShiftFlow.asSharedFlow()

    init {
        getStateShift()
    }

    fun setStateShift(isOpenedShift: Boolean) {
        viewModelScope.launch {
            _stateShiftFlow.emit(isOpenedShift)
            preferenceManager.saveStateShift(isOpenedShift)
        }
    }

    private fun getStateShift() {
        viewModelScope.launch {
            _stateShiftFlow.emit(preferenceManager.getStateShift())
        }
    }
}
