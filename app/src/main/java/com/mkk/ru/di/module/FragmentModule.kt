package com.mkk.ru.di.module

import com.mkk.ru.presentation.screen.SplashScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun bindSplashScreenFragment(): SplashScreenFragment
}
