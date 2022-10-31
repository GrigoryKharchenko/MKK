package com.mkk.ru.di.module

import com.mkk.ru.data.datastore.PreferenceManagerImpl
import com.mkk.ru.domain.preference.PreferenceManager
import dagger.Binds
import dagger.Module

@Module
interface PreferenceManagerModule {

    @Binds
    fun bindPreferenceManager(preferenceManagerImpl: PreferenceManagerImpl): PreferenceManager
}
