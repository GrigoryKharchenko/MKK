package com.mkk.ru.presentation.screen.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkk.ru.domain.preference.PreferenceManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PasswordViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _dotFlow = MutableStateFlow(PasswordUiState.DotUiState())
    val dotFlow = _dotFlow.asStateFlow()

    private val _textFlow = MutableStateFlow(PasswordUiState.TextUiState())
    val textFlow = _textFlow.asStateFlow()

    private val _nextScreenFlow = MutableSharedFlow<Unit>()
    val nextScreenFlow = _nextScreenFlow.asSharedFlow()

    private var lengthPassword = StringBuilder("")

    init {
        setUiState()
    }

    fun savePassword() {
        viewModelScope.launch {
            getPassword()
            preferenceManager.savePassword(lengthPassword.toString())
            lengthPassword.clear()
            _dotFlow.emit(PasswordUiState.DotUiState())
            _textFlow.emit(
                PasswordUiState.TextUiState(
                    isVisibleCreate = false,
                    isVisibleEnterCode = true,
                )
            )
        }
    }

    fun setValuePassword(value: String) {
        if (lengthPassword.length < MAX_LENGTH_PASSWORD) lengthPassword.append(value)
        setUiState()
    }

    fun deleteValuePassword() {
        if (lengthPassword.length in 1..MAX_LENGTH_PASSWORD) lengthPassword.deleteAt(lengthPassword.lastIndex)
        setUiState()
    }

    private fun getPassword() {
        viewModelScope.launch {
            preferenceManager.getPassword()
        }
    }

    private fun checkCode() {
        viewModelScope.launch {
            if (lengthPassword.toString() == preferenceManager.getPassword()) {
                _nextScreenFlow.emit(Unit)
            } else {
                lengthPassword.clear()
                _dotFlow.emit(
                    PasswordUiState.DotUiState(
                        enabledFirst = DotEnable.ERROR,
                        enabledSecond = DotEnable.ERROR,
                        enabledThird = DotEnable.ERROR,
                        enabledFourth = DotEnable.ERROR
                    )
                )
                delay(500)
                _dotFlow.emit(
                    PasswordUiState.DotUiState()
                )
            }
        }
    }

    private fun setUiState() {
        viewModelScope.launch {
            when (lengthPassword.length) {
                0 -> {
                    if (preferenceManager.getPassword().isEmpty()) {
                        _textFlow.emit(PasswordUiState.TextUiState())
                        _dotFlow.emit(PasswordUiState.DotUiState())
                    } else {
                        _dotFlow.emit(
                            PasswordUiState.DotUiState()
                        )
                        _textFlow.emit(
                            PasswordUiState.TextUiState(
                                isVisibleEnterCode = true,
                                isVisibleCreate = false
                            )
                        )
                    }
                }
                1 -> _dotFlow.emit(
                    PasswordUiState.DotUiState(
                        enabledFirst = DotEnable.SELECTED
                    )
                )
                2 -> _dotFlow.emit(
                    PasswordUiState.DotUiState(
                        enabledFirst = DotEnable.SELECTED,
                        enabledSecond = DotEnable.SELECTED
                    )
                )
                3 -> _dotFlow.emit(
                    PasswordUiState.DotUiState(
                        enabledFirst = DotEnable.SELECTED,
                        enabledSecond = DotEnable.SELECTED,
                        enabledThird = DotEnable.SELECTED,
                    )
                )
                else -> {
                    if (preferenceManager.getPassword().isEmpty()) {
                        _dotFlow.emit(
                            PasswordUiState.DotUiState(
                                enabledFirst = DotEnable.SELECTED,
                                enabledSecond = DotEnable.SELECTED,
                                enabledThird = DotEnable.SELECTED,
                                enabledFourth = DotEnable.SELECTED,
                            )
                        )
                        _textFlow.emit(
                            PasswordUiState.TextUiState(
                                isVisibleContinue = true
                            )
                        )
                    } else {
                        checkCode()
                        _dotFlow.emit(
                            PasswordUiState.DotUiState(
                                enabledFirst = DotEnable.SELECTED,
                                enabledSecond = DotEnable.SELECTED,
                                enabledThird = DotEnable.SELECTED,
                                enabledFourth = DotEnable.SELECTED,
                            )
                        )
                        _textFlow.emit(
                            PasswordUiState.TextUiState(
                                isVisibleEnterCode = true,
                                isVisibleCreate = false
                            )
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val MAX_LENGTH_PASSWORD = 4
    }
}
