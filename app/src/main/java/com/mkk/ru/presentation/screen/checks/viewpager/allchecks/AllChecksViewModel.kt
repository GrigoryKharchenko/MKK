package com.mkk.ru.presentation.screen.checks.viewpager.allchecks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkk.ru.domain.model.CheckModel
import com.mkk.ru.domain.repository.ChecksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllChecksViewModel @Inject constructor(
    private val checksRepository: ChecksRepository
) : ViewModel() {

    private val _allChecksFlow = MutableStateFlow<List<CheckModel>>(emptyList())
    val allChecksFlow = _allChecksFlow.asStateFlow()

    init {
        getCurrentChecks()
    }

    private fun getCurrentChecks() {
        viewModelScope.launch {
            _allChecksFlow.emit(checksRepository.getAllChecks())
        }
    }
}
