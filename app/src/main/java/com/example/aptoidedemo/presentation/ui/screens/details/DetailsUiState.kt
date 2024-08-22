package com.example.aptoidedemo.presentation.ui.screens.details

import com.example.aptoidedemo.core.models.local.Content

data class DetailsUiState(
    val loading: Boolean = false,
    val showDialog: Boolean = false,
    val content: Content = Content()
)