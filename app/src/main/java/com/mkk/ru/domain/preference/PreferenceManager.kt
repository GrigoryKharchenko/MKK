package com.mkk.ru.domain.preference

interface PreferenceManager {
    suspend fun getLastDate(): String
    suspend fun saveCurrentDate(currentDate: String)

    suspend fun getStateShift(): Boolean
    suspend fun saveStateShift(stateShift: Boolean)
}
