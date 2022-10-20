package com.mkk.ru.domain

import com.mkk.ru.presentation.screen.model.SubdivisionModel

interface SubdivisionsRepository {
    suspend fun getSubdivisions(): List<SubdivisionModel>
}
