package com.mkk.ru.presentation.screen.registrationcashbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkk.ru.domain.SubdivisionsRepository
import com.mkk.ru.presentation.screen.model.SubdivisionModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationCashBoxViewModel @Inject constructor(
    private val subdivisionsRepository: SubdivisionsRepository
) : ViewModel() {

    private var _subdivisionsFlow = MutableStateFlow<List<SubdivisionModel>>(emptyList())
    val subdivisionsFlow = _subdivisionsFlow.asStateFlow()

    init {
        getSubdivisions()
    }

    private fun getSubdivisions() {
        viewModelScope.launch {
            _subdivisionsFlow.emit(subdivisionsRepository.getSubdivisions())
        }
    }
}
