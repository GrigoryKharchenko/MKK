package com.mkk.ru.domain.model

data class CheckModel(
    val date: String,
    val time: String,
    val numberChecks: String,
    val isError: Boolean = true,
    val isPrinterOff: Boolean = true,
    val price:String
)
