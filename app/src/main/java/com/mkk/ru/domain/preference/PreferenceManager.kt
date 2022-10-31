package com.mkk.ru.domain.preference

interface PreferenceManager {
    suspend fun getLastDate(): String
    suspend fun saveCurrentDate(currentDate: String)
}
