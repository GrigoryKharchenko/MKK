package com.mkk.ru.data.repository

import com.mkk.ru.domain.repository.SubdivisionsRepository
import com.mkk.ru.domain.model.SubdivisionModel
import javax.inject.Inject

class SubdivisionRepositoryImpl @Inject constructor() : SubdivisionsRepository {
    override suspend fun getSubdivisions(): List<SubdivisionModel> =
        listOf(
            SubdivisionModel("УГНС по ЦОП г. Бишкек"),
            SubdivisionModel("УГНС по ЦОП г. Бишкек"),
            SubdivisionModel("УГНС по контролю за субъектами СЭЗ"),
            SubdivisionModel("УГНС по г. Ош"),
            SubdivisionModel("УККН по г. Бишкек и Северному региону"),
            SubdivisionModel(
                "УГНС по работе с косвенными налогами в рамках ЕАЭС по Чуйской, Таласской и Ошской областям и гг. Бишкек и Ош"
            )
        )
}
