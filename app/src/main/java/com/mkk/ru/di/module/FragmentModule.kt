package com.mkk.ru.di.module

import com.mkk.ru.presentation.screen.claimstatus.ClaimStatusFragment
import com.mkk.ru.presentation.screen.menu.MenuFragment
import com.mkk.ru.presentation.screen.registrationcashbox.RegistrationCashBoxFragment
import com.mkk.ru.presentation.screen.registrationpersonalaccount.RegistrationPersonalAccountFragment
import com.mkk.ru.presentation.screen.registrationrefusal.RegistrationRefusalFragment
import com.mkk.ru.presentation.screen.sale.SaleFragment
import com.mkk.ru.presentation.screen.splashscreen.SplashScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun bindSplashScreenFragment(): SplashScreenFragment

    @ContributesAndroidInjector
    fun bindRegistrationCashBoxFragment(): RegistrationCashBoxFragment

    @ContributesAndroidInjector
    fun bindClaimStatusFragment(): ClaimStatusFragment

    @ContributesAndroidInjector
    fun bindRegistrationRefusalFragment(): RegistrationRefusalFragment

    @ContributesAndroidInjector
    fun bindRegistrationPersonalAccountFragment(): RegistrationPersonalAccountFragment

    @ContributesAndroidInjector
    fun bindMenuFragment(): MenuFragment

    @ContributesAndroidInjector
    fun bindSaleFragment(): SaleFragment
}
