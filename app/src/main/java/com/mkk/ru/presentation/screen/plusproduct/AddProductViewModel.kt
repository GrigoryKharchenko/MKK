package com.mkk.ru.presentation.screen.plusproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mkk.ru.R
import com.mkk.ru.domain.repository.ProductRepository
import com.mkk.ru.presentation.screen.sale.ProductUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class AddProductViewModel @Inject constructor(
    private val productsRepository: ProductRepository
) : ViewModel() {

    private val _calculateFlow = MutableStateFlow(INIT_VALUE)
    val calculateFlow = _calculateFlow.asSharedFlow()

    private val _selectedUnitFlow = MutableStateFlow(TypeUnits.UNDEFINE)
    val selectedUnitFlow = _selectedUnitFlow.asStateFlow()

    private val _unitsFlow = MutableStateFlow(TypeUnits.values().toList())
    val unitsFlow = _unitsFlow.asStateFlow()

    private val _errorFlow = MutableStateFlow(ErrorValidation())
    val errorFlow = _errorFlow.asStateFlow()

    private val _backFlow = MutableSharedFlow<Unit>()
    val backFlow = _backFlow.asSharedFlow()

    fun calculateSum(price: String?, amount: String?) {
        viewModelScope.launch {
            val safePrise = price?.toDoubleOrNull() ?: INIT_VALUE
            val safeAmount = amount?.toDoubleOrNull() ?: INIT_VALUE
            _calculateFlow.emit((safeAmount * safePrise))
        }
    }

    fun setSelectedUnit(position: Int) {
        viewModelScope.launch {
            _selectedUnitFlow.emit(_unitsFlow.value[position])
        }
    }

    fun hideProductError() {
        viewModelScope.launch {
            _errorFlow.update {
                it.copy(errorProduct = null)
            }
        }
    }

    fun hidePriceError() {
        viewModelScope.launch {
            _errorFlow.update {
                it.copy(errorPrice = null)
            }
        }
    }

    fun hideAmountError() {
        viewModelScope.launch {
            _errorFlow.update {
                it.copy(errorAmount = null)
            }
        }
    }

    fun hideProductCodeError() {
        viewModelScope.launch {
            _errorFlow.update {
                it.copy(errorProductCode = null)
            }
        }
    }

    fun addProducts(
        productName: String,
        price: String,
        amount: String,
        productCode: String,
    ) {
        viewModelScope.launch {
            if (productName.isNotEmpty() && price.isNotEmpty()
                && amount.isNotEmpty() && productCode.isNotEmpty()
            ) {
                productsRepository.addProduct(
                    ProductUiModel(
                        product = productName,
                        price = price,
                        amount = amount,
                        productCode = productCode,
                        generalPrice = _calculateFlow.value
                    )
                )
                _backFlow.emit(Unit)
            } else {
                _errorFlow.emit(
                    ErrorValidation(
                        if (productName.isEmpty()) R.string.add_product_invalid_product else null,
                        if (price.isEmpty()) R.string.add_product_invalid_price else null,
                        if (amount.isEmpty()) R.string.add_product_invalid_amount else null,
                        if (productCode.isEmpty()) R.string.add_product_invalid_product_code else null,
                    )
                )
            }
        }
    }

    companion object {
        private const val INIT_VALUE = 0.0
    }
}

