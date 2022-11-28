package com.mkk.ru.di

import android.content.Context
import com.mkk.ru.MkkApp
import com.mkk.ru.di.module.FragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

//@FragmentScope
//@Component(
//    modules = [
//        AndroidSupportInjectionModule::class,
//        AndroidInjectionModule::class,
//        FragmentModule::class]
//)
//interface FragmentComponent : AndroidInjector<MkkApp> {
//
//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun context(context: Context): FragmentComponent.Builder
//
//        fun build(): FragmentComponent
//    }
//}
