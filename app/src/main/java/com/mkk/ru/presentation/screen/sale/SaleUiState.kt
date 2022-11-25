package com.mkk.ru.presentation.screen.sale

data class SaleUiState(
    val products: List<ProductUiModel> = emptyList(),
    val hasProducts: Boolean = false
)
