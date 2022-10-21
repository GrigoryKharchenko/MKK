package com.mkk.ru.presentation.screen.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor() : ViewModel() {

    private var _openRegistrationCashBoxFragmentFlow = MutableSharedFlow<Unit>()
    val openRegistrationCashBoxFragmentFlow = _openRegistrationCashBoxFragmentFlow.asSharedFlow()

    init {
        openRegistrationFragment()
    }

    private fun openRegistrationFragment() {
        viewModelScope.launch {
            delay(TIME_FOR_OPEN_FRAGMENT)
            _openRegistrationCashBoxFragmentFlow.emit(Unit)
        }
    }

    companion object {
        private const val TIME_FOR_OPEN_FRAGMENT = 1000L
    }
}
