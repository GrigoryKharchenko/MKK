package com.mkk.ru

import android.content.Context
import com.mkk.ru.di.component.AppComponent
import com.mkk.ru.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MkkApp : DaggerApplication() {

    lateinit var appComponent: AppComponent
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .context(this)
            .build().also {
                appComponent = it
            }
}

val Context.appComp: AppComponent
    get() = (applicationContext as MkkApp).appComponent
