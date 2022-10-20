package com.mkk.ru.di

import android.content.Context
import com.mkk.ru.MkkApp
import com.mkk.ru.di.module.ActivityModule
import com.mkk.ru.di.module.FragmentModule
import com.mkk.ru.di.module.RepositoryModule
import com.mkk.ru.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
    ]
)
interface AppComponent : AndroidInjector<MkkApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}
