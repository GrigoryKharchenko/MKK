package com.mkk.ru.di.module

import androidx.lifecycle.ViewModel
import com.mkk.ru.presentation.screen.SplashScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    fun bindSplashScreenViewModel(viewMode: SplashScreenViewModel): ViewModel
}
