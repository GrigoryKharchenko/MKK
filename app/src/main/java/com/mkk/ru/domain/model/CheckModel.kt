package com.mkk.ru.domain.model

data class CheckModel(
    val date: String,
    val time: String,
    val idChecks: String,
    val isInvisibleErrorIcon: Boolean = true,
    val isInvisiblePrinterOffIcon: Boolean = true,
    val price:String
)
