package com.example.aptoidedemo.presentation.ui.screens.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aptoidedemo.core.managers.AptoideManager
import com.example.aptoidedemo.core.models.result.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val aptoideManager: AptoideManager
) : ViewModel() {
    private val _state = MutableStateFlow(LandingUiState())
    val state = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            aptoideManager.observe()
                .onEach {
                    if (it.isEmpty()) {
                        _state.emit(_state.value.copy(isLoading = true))
                        when (val result = aptoideManager.fetchData()) {
                            is ResultHandler.Error ->
                                _state.emit(
                                    _state.value.copy(
                                        isLoading = false,
                                        message = "${result.exception}"
                                    )
                                )

                            is ResultHandler.Success -> {}
                        }
                    }
                }
                .collectLatest { list ->
                    _state.emit(_state.value.copy(data = list, isLoading = false))
                }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            when (val result = aptoideManager.fetchData()) {
                is ResultHandler.Error ->
                    _state.emit(_state.value.copy(message = "${result.exception}"))

                is ResultHandler.Success ->
                    _state.emit(_state.value.copy(message = ""))
            }
        }
    }

    fun showMessage() = viewModelScope.launch {
        _state.emit(_state.value.copy(message = "Notifications are turn off, go to App settings and change"))
    }
}