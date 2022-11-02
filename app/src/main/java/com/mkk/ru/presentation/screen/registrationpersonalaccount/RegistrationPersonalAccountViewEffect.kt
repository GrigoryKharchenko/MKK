package com.mkk.ru.presentation.screen.registrationpersonalaccount

import androidx.annotation.StringRes

sealed class RegistrationPersonalAccountViewEffect {
    data class ErrorValidationUiModel(
        @StringRes val emailMessage: Int?,
        @StringRes val passwordMessage: Int?
    ) : RegistrationPersonalAccountViewEffect()
    object SuccessRegistration : RegistrationPersonalAccountViewEffect()
    object FailureRegistration : RegistrationPersonalAccountViewEffect()
    object ShowProgressBar : RegistrationPersonalAccountViewEffect()
}
