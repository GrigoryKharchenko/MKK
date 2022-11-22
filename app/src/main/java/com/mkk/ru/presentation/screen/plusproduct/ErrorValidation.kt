package com.mkk.ru.presentation.screen.plusproduct

data class ErrorValidation(
    val errorProduct: Int? = null,
    val errorPrice: Int?= null,
    val errorAmount: Int? = null,
    val errorCodeProduct: Int? = null,
)
