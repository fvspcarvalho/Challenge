package com.example.aptoidedemo.presentation.ui.resources

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BaseScreen(
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    Scaffold(
        topBar = { topBar() }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)
        ) {
            content()
        }
    }
}