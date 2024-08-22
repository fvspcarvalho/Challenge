package com.example.aptoidedemo.presentation.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aptoidedemo.core.managers.AptoideManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val aptoideManager: AptoideManager
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsUiState())
    val state = _state.asStateFlow()

    fun getAppName(id: Long) {
        viewModelScope.launch {
            val content = aptoideManager.getContent(id)
            _state.emit(_state.value.copy(content = content))
        }
    }

    fun onClick(showDialog: Boolean) =
        viewModelScope.launch { _state.emit(_state.value.copy(showDialog = showDialog)) }
}