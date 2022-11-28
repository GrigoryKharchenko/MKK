package com.mkk.ru.domain.preference

interface PreferenceManager {
    suspend fun getCurrentDate(): String
    suspend fun saveCurrentDate(currentDate: String)

    suspend fun getStateShift(): Boolean
    suspend fun saveStateShift(stateShift: Boolean)
}
