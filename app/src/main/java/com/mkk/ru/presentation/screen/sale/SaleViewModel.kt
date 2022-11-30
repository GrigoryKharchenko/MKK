package com.mkk.ru.presentation.screen.sale

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkk.ru.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaleViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productFlow = MutableStateFlow(SaleUiState())
    val productFlow = _productFlow.asStateFlow()

    private val _productCostFlow = MutableStateFlow(INIT_VALUE)
    val productCostFlow = _productCostFlow.asStateFlow()

    init {
        setProduct()
    }

    private fun setProduct() {
        viewModelScope.launch {
            productRepository.productFlow.collect { products ->
                _productFlow.emit(SaleUiState(products = products, hasProducts = products.isNotEmpty()))
                _productCostFlow.emit(products.sumOf {
                    it.generalPrice
                })
            }
        }
    }

    override fun onCleared() {
        productRepository.clearProducts()
        super.onCleared()
    }

    companion object {
        private const val INIT_VALUE = 0.0
    }
}
