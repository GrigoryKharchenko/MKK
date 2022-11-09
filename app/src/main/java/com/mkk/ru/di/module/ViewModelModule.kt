package com.mkk.ru.di.module

import androidx.lifecycle.ViewModel
import com.mkk.ru.di.ViewModelKey
import com.mkk.ru.presentation.screen.claimstatus.ClaimStatusViewModel
import com.mkk.ru.presentation.screen.menu.MenuViewModel
import com.mkk.ru.presentation.screen.registrationcashbox.RegistrationCashBoxViewModel
import com.mkk.ru.presentation.screen.registrationpersonalaccount.RegistrationPersonalAccountViewModel
import com.mkk.ru.presentation.screen.registrationrefusal.RegistrationRefusalViewModel
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
    @ViewModelKey(ClaimStatusViewModel::class)
    fun bindClaimStatusViewModel(viewModel: ClaimStatusViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationRefusalViewModel::class)
    fun bindRegistrationRefusalViewModel(viewModel: RegistrationRefusalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationPersonalAccountViewModel::class)
    fun bindRegistrationInPersonalAccountViewModel(viewModel: RegistrationPersonalAccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    fun bindMenuViewModel(viewModel: MenuViewModel): ViewModel
}
