package com.mkk.ru.presentation.screen.plusproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.RoundingMode
import javax.inject.Inject

class AddProductViewModel @Inject constructor() : ViewModel() {

    private val _calculateFlow = MutableSharedFlow<Double>(replay = 1)
    val calculateFlow = _calculateFlow.asSharedFlow()

    private val _selectedUnitFlow = MutableStateFlow(TypeUnits.UNDEFINE)
    val selectedUnitFlow = _selectedUnitFlow.asStateFlow()

    private val _unitsFlow = MutableStateFlow(TypeUnits.values().toList())
    val unitsFlow = _unitsFlow.asStateFlow()

    fun calculateSum(price: String?, amount: String?) {
        viewModelScope.launch {
            val safePrise = price?.toDoubleOrNull() ?: 0.0
            val safeAmount = amount?.toDoubleOrNull() ?: 0.0
            _calculateFlow.emit((safeAmount * safePrise))
        }
    }

    fun setSelectedUnit(position: Int) {
        viewModelScope.launch {
            _selectedUnitFlow.emit(_unitsFlow.value[position])
        }
    }
}
