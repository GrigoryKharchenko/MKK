package com.mkk.ru.di.fragmentcomponent

import androidx.lifecycle.ViewModel
import com.mkk.ru.data.repository.ProductRepositoryImpl
import com.mkk.ru.di.ViewModelKey
import com.mkk.ru.di.fragmentcomponent.scope.FragmentScope
import com.mkk.ru.domain.repository.ProductRepository
import com.mkk.ru.presentation.screen.plusproduct.AddProductViewModel
import com.mkk.ru.presentation.screen.sale.SaleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SaleFragmentModule {

    @Binds
    @FragmentScope
    fun bindProductRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository

    @Binds
    @IntoMap
    @ViewModelKey(AddProductViewModel::class)
    fun bindAddProductViewModel(viewModel: AddProductViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SaleViewModel::class)
    fun binSaleViewModel(viewModel: SaleViewModel): ViewModel
}
