package com.mkk.ru.di.module

import com.mkk.ru.presentation.screen.registrationcashbox.RegistrationCashBoxFragment
import com.mkk.ru.presentation.screen.splashscreen.SplashScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun bindSplashScreenFragment(): SplashScreenFragment

    @ContributesAndroidInjector
    fun bindRegistrationCashBoxFragment(): RegistrationCashBoxFragment
}
