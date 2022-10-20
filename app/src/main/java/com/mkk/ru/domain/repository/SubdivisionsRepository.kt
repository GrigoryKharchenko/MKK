package com.mkk.ru.domain.repository

import com.mkk.ru.domain.model.SubdivisionModel

interface SubdivisionsRepository {
    suspend fun getSubdivisions(): List<SubdivisionModel>
}
