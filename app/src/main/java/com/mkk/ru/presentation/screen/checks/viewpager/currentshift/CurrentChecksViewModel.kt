package com.mkk.ru.presentation.screen.checks.viewpager.currentshift

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkk.ru.domain.model.CheckModel
import com.mkk.ru.domain.repository.ChecksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentChecksViewModel @Inject constructor(
    private val checksRepository: ChecksRepository
) : ViewModel() {

    private val _currentChecksFlow = MutableStateFlow<List<CheckModel>>(emptyList())
    val currentChecksFlow = _currentChecksFlow.asStateFlow()

    init {
        getCurrentChecks()
    }

    private fun getCurrentChecks() {
        viewModelScope.launch {
            _currentChecksFlow.emit(checksRepository.getCurrentChecks())
        }
    }
}
