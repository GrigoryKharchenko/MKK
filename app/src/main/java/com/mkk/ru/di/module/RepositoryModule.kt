package com.mkk.ru.di.module

import com.mkk.ru.data.LoginRepositoryImpl
import com.mkk.ru.data.SubdivisionRepositoryImpl
import com.mkk.ru.domain.repository.LoginRepository
import com.mkk.ru.domain.repository.SubdivisionsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindSubdivisionRepository(repositoryImpl: SubdivisionRepositoryImpl): SubdivisionsRepository

    @Binds
    fun bindLoginRepository(repositoryImpl: LoginRepositoryImpl): LoginRepository

}
