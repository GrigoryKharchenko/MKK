package com.mkk.ru.di.fragmentcomponent

import com.mkk.ru.di.component.AppComponent
import com.mkk.ru.di.fragmentcomponent.scope.FragmentScope
import com.mkk.ru.presentation.screen.plusproduct.AddProductFragment
import com.mkk.ru.presentation.screen.sale.SaleFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [AppComponent::class],
    modules = [SaleFragmentModule::class]
)
interface FragmentComponent {

    fun inject(saleFragment: SaleFragment)

    fun inject(addProductFragment: AddProductFragment)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent
        ): FragmentComponent
    }
}
