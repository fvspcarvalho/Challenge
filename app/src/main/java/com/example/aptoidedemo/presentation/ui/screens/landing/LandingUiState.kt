package com.example.aptoidedemo.presentation.ui.screens.landing

import com.example.aptoidedemo.core.models.local.Content

data class LandingUiState(
    val isLoading: Boolean = false,
    val message: String = "",
    val data: List<Content> = emptyList()
)