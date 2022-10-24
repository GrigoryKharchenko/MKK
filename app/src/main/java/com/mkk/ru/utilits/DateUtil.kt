package com.mkk.ru.utilits

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtil {

    private const val PATTERN = "dd.MM.yyyy HH:mm"

    fun getCurrentDate(): String = SimpleDateFormat(PATTERN, Locale.getDefault()).format(System.currentTimeMillis())
}
