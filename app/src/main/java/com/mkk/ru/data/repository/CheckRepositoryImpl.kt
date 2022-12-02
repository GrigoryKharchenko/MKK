package com.mkk.ru.data.repository

import com.mkk.ru.domain.model.CheckModel
import com.mkk.ru.domain.repository.ChecksRepository
import javax.inject.Inject

class CheckRepositoryImpl @Inject constructor() : ChecksRepository {
    override suspend fun getCurrentChecks(): List<CheckModel> =
        listOf(
            CheckModel(
                date = "16.02.21",
                time = "16:01",
                numberChecks = "№2323513224",
                price = "125"
            ),
            CheckModel(
                date = "16.02.21",
                time = "16:01",
                numberChecks = "№2323513224",
                price = "125"
            ),
            CheckModel(
                date = "16.02.21",
                time = "16:01",
                numberChecks = "№2323513224",
                isError = false,
                isPrinterOff = false,
                price = "125"
            ),
            CheckModel(
                date = "16.02.21",
                time = "16:01",
                numberChecks = "№2323513224",
                price = "125"
            ),
        )

    override suspend fun getAllChecks(): List<CheckModel> =
        listOf(
            CheckModel(
                date = "16.02.21",
                time = "16:01",
                numberChecks = "№2323513224",
                price = "125"
            ),
            CheckModel(
                date = "16.02.21",
                time = "16:01",
                numberChecks = "№2323513224",
                isError = false,
                isPrinterOff = false,
                price = "125"
            ),
            CheckModel(
                date = "16.02.21",
                time = "16:01",
                numberChecks = "№2323513224",
                price = "125"
            ),
            CheckModel(
                date = "16.02.21",
                time = "16:01",
                numberChecks = "№2323513224",
                isError = false,
                isPrinterOff = false,
                price = "125"
            ),
        )
}
