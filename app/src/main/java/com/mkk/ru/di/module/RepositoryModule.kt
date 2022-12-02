package com.mkk.ru.di.module

import com.mkk.ru.data.repository.CheckRepositoryImpl
import com.mkk.ru.data.repository.LoginRepositoryImpl
import com.mkk.ru.data.repository.ProductRepositoryImpl
import com.mkk.ru.data.repository.SubdivisionRepositoryImpl
import com.mkk.ru.domain.repository.ChecksRepository
import com.mkk.ru.domain.repository.LoginRepository
import com.mkk.ru.domain.repository.ProductRepository
import com.mkk.ru.domain.repository.SubdivisionsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    fun bindSubdivisionRepository(repositoryImpl: SubdivisionRepositoryImpl): SubdivisionsRepository

    @Binds
    fun bindLoginRepository(repositoryImpl: LoginRepositoryImpl): LoginRepository

    @Singleton
    @Binds
    fun bindProductRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository

    @Binds
    fun bindCheckRepository(repositoryImpl: CheckRepositoryImpl): ChecksRepository
}
