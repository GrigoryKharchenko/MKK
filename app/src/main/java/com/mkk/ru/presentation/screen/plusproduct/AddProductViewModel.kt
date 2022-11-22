package com.mkk.ru.presentation.screen.plusproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkk.ru.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddProductViewModel @Inject constructor() : ViewModel() {

    private val _calculateFlow = MutableSharedFlow<Double>(replay = 1)
    val calculateFlow = _calculateFlow.asSharedFlow()

    private val _selectedUnitFlow = MutableStateFlow(TypeUnits.UNDEFINE)
    val selectedUnitFlow = _selectedUnitFlow.asStateFlow()

    private val _unitsFlow = MutableStateFlow(TypeUnits.values().toList())
    val unitsFlow = _unitsFlow.asStateFlow()

    private val _errorFlow = MutableStateFlow(ErrorValidation())
    val errorFlow = _errorFlow.asStateFlow()

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

    fun checkProduct() {
        viewModelScope.launch {
            _errorFlow.update {
                it.copy(errorProduct = null)
            }
        }
    }

    fun checkPrice() {
        viewModelScope.launch {
            _errorFlow.update {
                it.copy(errorPrice = null)
            }
        }
    }

    fun checkAmount() {
        viewModelScope.launch {
            _errorFlow.update {
                it.copy(errorAmount = null)
            }
        }
    }

    fun checkCodeProduct() {
        viewModelScope.launch {
            _errorFlow.update {
                it.copy(errorCodeProduct = null)
            }
        }
    }

    fun setErrors(
        product: String,
        price: String,
        amount: String,
        codeProduct: String
    ) {
        viewModelScope.launch {
            _errorFlow.emit(
                ErrorValidation(
                    if (product.isEmpty()) R.string.add_product_invalid_product else null,
                    if (price.isEmpty()) R.string.add_product_invalid_price else null,
                    if (amount.isEmpty()) R.string.add_product_invalid_amount else null,
                    if (codeProduct.isEmpty()) R.string.add_product_invalid_code_product else null,
                )
            )
        }
    }
}
