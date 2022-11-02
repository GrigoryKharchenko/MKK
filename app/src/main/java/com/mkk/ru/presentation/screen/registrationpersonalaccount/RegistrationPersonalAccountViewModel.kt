package com.mkk.ru.presentation.screen.registrationpersonalaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkk.ru.R
import com.mkk.ru.domain.repository.LoginRepository
import com.mkk.ru.extension.isValidEmail
import com.mkk.ru.extension.isValidPassword
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationPersonalAccountViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
) : ViewModel() {

    private var _viewEffectFlow = MutableSharedFlow<RegistrationPersonalAccountViewEffect>()
    val viewEffectFlow = _viewEffectFlow.asSharedFlow()

    fun checkValidation(email: String, password: String) {
        viewModelScope.launch {
            val isValidEmail = email.isValidEmail()
            val isValidPassword = password.isValidPassword()
            if (isValidEmail && isValidPassword) {
                register()
            } else {
                _viewEffectFlow.emit(
                    RegistrationPersonalAccountViewEffect.ErrorValidationUiModel(
                        emailMessage = if (isValidEmail) null else R.string.registration_in_personal_account_error_email,
                        passwordMessage = if (isValidPassword) null else R.string.registration_in_personal_account_error_password
                    )
                )
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            _viewEffectFlow.emit(RegistrationPersonalAccountViewEffect.ShowProgressBar)
            runCatching {
                loginRepository.login()
            }.onSuccess {
                _viewEffectFlow.emit(RegistrationPersonalAccountViewEffect.SuccessRegistration)
            }.onFailure {
                _viewEffectFlow.emit(RegistrationPersonalAccountViewEffect.FailureRegistration)
            }
        }
    }
}
