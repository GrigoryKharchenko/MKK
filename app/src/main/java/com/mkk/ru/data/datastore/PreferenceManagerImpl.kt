package com.mkk.ru.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mkk.ru.domain.preference.PreferenceManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")

class PreferenceManagerImpl @Inject constructor(
    private val context: Context
) : PreferenceManager {

    private val currentDateKey = stringPreferencesKey("key_current_date")

    private val currentDate = context.dataStore.data.map { currentDate ->
        currentDate[currentDateKey] ?: ""
    }

    private val stateShiftKey = booleanPreferencesKey("key_state_shift")

    private val stateShiftFlow = context.dataStore.data.map { preferences ->
        preferences[stateShiftKey] ?: false
    }

    private val passwordKey = stringPreferencesKey("password_key")

    private val passwordFlow = context.dataStore.data.map { preference ->
        preference[passwordKey] ?: ""
    }

    override suspend fun getCurrentDate(): String = currentDate.first()

    override suspend fun saveCurrentDate(currentDate: String) {
        context.dataStore.edit { date ->
            date[currentDateKey] = currentDate
        }
    }

    override suspend fun getStateShift(): Boolean = stateShiftFlow.first()

    override suspend fun saveStateShift(stateShift: Boolean) {
        context.dataStore.edit { state ->
            state[stateShiftKey] = stateShift
        }
    }

    override suspend fun getPassword(): String = passwordFlow.first()

    override suspend fun savePassword(password: String) {
        context.dataStore.edit { passwordEdit ->
            passwordEdit[passwordKey] = password
        }
    }
}
