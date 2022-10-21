package com.mkk.ru.di.module

import androidx.lifecycle.ViewModel
import com.mkk.ru.di.ViewModelKey
import com.mkk.ru.presentation.screen.registrationcashbox.RegistrationCashBoxViewModel
import com.mkk.ru.presentation.screen.requestacceptance.RequestAcceptanceViewModel
import com.mkk.ru.presentation.screen.splashscreen.SplashScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    fun bindSplashScreenViewModel(viewModel: SplashScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationCashBoxViewModel::class)
    fun bindRegistrationCashBoxViewModel(viewModel: RegistrationCashBoxViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RequestAcceptanceViewModel::class)
    fun bindRequestAcceptanceViewModel(viewModel: RequestAcceptanceViewModel): ViewModel

}
