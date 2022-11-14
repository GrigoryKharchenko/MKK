package com.mkk.ru.presentation.screen.plusproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddProductViewModel @Inject constructor() : ViewModel() {

    private val _calculateFlow = MutableSharedFlow<String>(replay = 1)
    val calculateFlow = _calculateFlow.asSharedFlow()

    private val _unitsFlow = MutableStateFlow(TypeUnits.UNITS)
    val unitsFlow = _unitsFlow.asSharedFlow()

    fun calculateSum(price: String?, amount: String?) {
        viewModelScope.launch {
            val safePrise = price?.toDoubleOrNull() ?: 0.0
            val safeAmount = amount?.toDoubleOrNull() ?: 0.0
            _calculateFlow.emit((safeAmount * safePrise).toString())
        }
    }
}
