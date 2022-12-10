package com.mkk.ru.presentation.screen.password

sealed class PasswordUiState {
    data class DotUiState(
        val enabledFirst: DotEnable = DotEnable.CHECKED,
        val enabledSecond: DotEnable = DotEnable.CHECKED,
        val enabledThird: DotEnable = DotEnable.CHECKED,
        val enabledFourth: DotEnable = DotEnable.CHECKED,

    ) : PasswordUiState()
    data class TextUiState(
        val isVisibleCreate: Boolean = true,
        val isVisibleContinue: Boolean = false,
        val isVisibleEnterCode: Boolean = false
    ): PasswordUiState()
}

