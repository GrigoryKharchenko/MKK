package com.mkk.ru.domain.repository

import com.mkk.ru.presentation.screen.sale.ProductUiModel
import kotlinx.coroutines.flow.SharedFlow

interface ProductRepository {
    val productFlow: SharedFlow<List<ProductUiModel>>
    suspend fun addProduct(product: ProductUiModel)
    suspend fun deleteProduct(product: ProductUiModel)
    fun clearProducts()
}
