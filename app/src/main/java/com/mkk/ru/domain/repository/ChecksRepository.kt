package com.mkk.ru.domain.repository

import com.mkk.ru.domain.model.CheckModel

interface ChecksRepository {
    suspend fun getCurrentChecks(): List<CheckModel>
    suspend fun getAllChecks(): List<CheckModel>
}
