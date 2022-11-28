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

    init {
        setProduct()
    }

    private fun setProduct() {
        viewModelScope.launch {
            productRepository.productFlow.collect {
                _productFlow.emit(SaleUiState(products = it, hasProducts = it.isNotEmpty()))
            }
        }
    }
}
