package com.example.aptoidedemo.presentation.ui.details

import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aptoidedemo.data.local.Root
import com.example.aptoidedemo.data.manager.AptoideManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val aptoideManager: AptoideManager
) : ViewModel() {

    var detailsUiState: DetailsUiState by mutableStateOf(DetailsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            detailsUiState = DetailsUiState.Loading
            detailsUiState = try {
                DetailsUiState.Success(aptoideManager.getData().responses.listApps.datasets.all.data.content.map { it.graphic })
            } catch (e: IOException) {
                DetailsUiState.Error
            } catch (e: HttpException) {
                DetailsUiState.Error
            }
        }
    }

}

sealed interface DetailsUiState {
    data object Loading : DetailsUiState
    data class Success(val data: List<String>) : DetailsUiState
    data object Error : DetailsUiState
}