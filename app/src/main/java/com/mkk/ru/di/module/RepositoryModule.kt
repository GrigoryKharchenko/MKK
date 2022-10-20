package com.mkk.ru.di.module

import com.mkk.ru.data.SubdivisionRepositoryImpl
import com.mkk.ru.domain.SubdivisionsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindSubdivisionRepository(repositoryImpl: SubdivisionRepositoryImpl): SubdivisionsRepository

}
