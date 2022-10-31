package com.mkk.ru.presentation.screen.registrationcashbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkk.ru.R
import com.mkk.ru.domain.model.SubdivisionModel
import com.mkk.ru.domain.repository.LoginRepository
import com.mkk.ru.domain.repository.SubdivisionsRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationCashBoxViewModel @Inject constructor(
    private val subdivisionsRepository: SubdivisionsRepository,
    private val loginRepository: LoginRepository,
) : ViewModel() {

    private var _subdivisionsFlow = MutableStateFlow<List<SubdivisionModel>>(emptyList())
    val subdivisionsFlow = _subdivisionsFlow.asStateFlow()

    private var _showSnackbarFlow = MutableSharedFlow<Int>()
    val showSnackbarFlow = _showSnackbarFlow.asSharedFlow()

    private var _openRequestAcceptanceFragmentFlow = MutableSharedFlow<Unit>()
    val openRequestAcceptanceFragmentFlow = _openRequestAcceptanceFragmentFlow.asSharedFlow()

    private var _statusProgressBarFlow = MutableSharedFlow<Boolean>()
    val statusProgressBarFlow = _statusProgressBarFlow.asSharedFlow()

    init {
        getSubdivisions()
    }

    private fun getSubdivisions() {
        viewModelScope.launch {
            _subdivisionsFlow.emit(subdivisionsRepository.getSubdivisions())
        }
    }

    fun checkValidation(
        nameSubject: String,
        innSubject: String,
        addressSubject: String,
        dislocationTax: String,
        taxMode: String,
        typeAction: String,
        contactNumber: String,
        typeObject: String,
        nameObject: String,
        addressObject: String
    ) {
        viewModelScope.launch {
            when {
                nameSubject.isEmpty() -> _showSnackbarFlow.emit(R.string.error_name_subject)
                innSubject.length < MAX_LENGTH_INN -> _showSnackbarFlow.emit(R.string.error_inn_subject)
                addressSubject.isEmpty() -> _showSnackbarFlow.emit(R.string.error_address_subject)
                dislocationTax.isEmpty() -> _showSnackbarFlow.emit(R.string.error_dislocation_tax)
                taxMode.isEmpty() -> _showSnackbarFlow.emit(R.string.error_tax_mode)
                typeAction.isEmpty() -> _showSnackbarFlow.emit(R.string.error_type_action)
                contactNumber.length < MAX_LENGTH_PHONE_NUMBER -> _showSnackbarFlow.emit(R.string.error_type_object)
                typeObject.isEmpty() -> _showSnackbarFlow.emit(R.string.error_type_object)
                nameObject.isEmpty() -> _showSnackbarFlow.emit(R.string.error_name_object)
                addressObject.isEmpty() -> _showSnackbarFlow.emit(R.string.error_address_object)
                else -> sendRequest()
            }
        }
    }

    private fun sendRequest() {
        viewModelScope.launch {
            _statusProgressBarFlow.emit(true)
            runCatching {
                loginRepository.login()
            }.onSuccess {
                _statusProgressBarFlow.emit(false)
                _openRequestAcceptanceFragmentFlow.emit(Unit)
            }.onFailure {
                _statusProgressBarFlow.emit(false)
                _showSnackbarFlow.emit(R.string.error_request)
            }
        }
    }

    companion object {
        private const val MAX_LENGTH_INN = 14
        private const val MAX_LENGTH_PHONE_NUMBER = 12
    }
}
