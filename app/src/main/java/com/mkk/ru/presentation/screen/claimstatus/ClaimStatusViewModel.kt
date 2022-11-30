package com.mkk.ru.presentation.screen.claimstatus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkk.ru.domain.preference.PreferenceManager
import com.mkk.ru.domain.repository.LoginRepository
import com.mkk.ru.utilits.DateUtil
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ClaimStatusViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private var _statusProgressBarFlow = MutableSharedFlow<Boolean>()
    val statusProgressBarFlow = _statusProgressBarFlow.asSharedFlow()

    private var _currentDateFlow = MutableSharedFlow<String>(replay = 1)
    val currentDateFlow = _currentDateFlow.asSharedFlow()

    private var _openRegistrationRefusalFragment = MutableSharedFlow<Unit>()
    val openRegistrationRefusalFragment = _openRegistrationRefusalFragment.asSharedFlow()

    private var _showDialogFlow = MutableSharedFlow<Unit>()
    val showDialogFlow = _showDialogFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            _currentDateFlow.emit(preferenceManager.getCurrentDate())
        }
    }

    fun updateRequest() {
        viewModelScope.launch {
            _statusProgressBarFlow.emit(true)
            runCatching {
                loginRepository.login()
            }.onSuccess {
                _statusProgressBarFlow.emit(false)
                val currentDate = DateUtil.getCurrentDate()
                preferenceManager.saveCurrentDate(currentDate)
                _currentDateFlow.emit(currentDate)
                _showDialogFlow.emit(Unit)
            }.onFailure {
                _openRegistrationRefusalFragment.emit(Unit)
            }
        }
    }
}
