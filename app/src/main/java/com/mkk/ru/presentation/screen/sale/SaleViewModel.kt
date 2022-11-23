package com.mkk.ru.presentation.screen.sale

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SaleUiState(
    val products: List<ProductUiModel> = emptyList(),
    val hasProducts: Boolean = false
)

class SaleViewModel @Inject constructor() : ViewModel() {

    private val _productFlow = MutableStateFlow(SaleUiState())
    val productFlow = _productFlow.asStateFlow()

    init {
        setProduct()
    }

    private fun setProduct() {
        viewModelScope.launch {
            val listProduct = listOf(
                ProductUiModel(
                    id = 0, product = "Sik", price = "456", amount = "2", productCode = "454", generalPrice = "544"
                ), ProductUiModel(
                    id = 1, product = "Snik", price = "45", amount = "3", productCode = "5454", generalPrice = "555"
                ), ProductUiModel(
                    id = 2, product = "Snik", price = "4", amount = "4", productCode = "6655", generalPrice = "0258"
                ), ProductUiModel(
                    id = 0, product = "Sik", price = "456", amount = "2", productCode = "454", generalPrice = "544"
                ),
            )
            _productFlow.emit(SaleUiState(products = listProduct, hasProducts = listProduct.isNotEmpty()))
        }
    }
}



