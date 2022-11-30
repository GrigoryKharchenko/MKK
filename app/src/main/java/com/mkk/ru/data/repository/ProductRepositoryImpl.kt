package com.mkk.ru.data.repository

import com.mkk.ru.domain.repository.ProductRepository
import com.mkk.ru.presentation.screen.sale.ProductUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor() : ProductRepository {
    private val products = mutableListOf<ProductUiModel>()
    private val _productsFlow = MutableSharedFlow<List<ProductUiModel>>(replay = 1)
    override val productFlow: SharedFlow<List<ProductUiModel>> = _productsFlow.asSharedFlow()

    override suspend fun addProduct(product: ProductUiModel) {
        products.add(product)
        _productsFlow.emit(products)
    }

    override suspend fun deleteProduct(product: ProductUiModel) {
        products.remove(product)
        _productsFlow.emit(products)
    }

    override fun clearProducts() {
        products.clear()
        _productsFlow.tryEmit(products)
    }
}
