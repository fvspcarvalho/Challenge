package com.example.aptoidedemo.presentation.ui.screens.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aptoidedemo.core.models.local.Content
import com.example.aptoidedemo.presentation.ui.resources.AppCard
import com.example.aptoidedemo.presentation.ui.resources.BaseScreen
import com.example.aptoidedemo.presentation.ui.resources.BasicTopBar
import com.example.aptoidedemo.presentation.ui.resources.rememberPermissionState

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    viewModel: LandingViewModel = hiltViewModel(),
    onGranted: () -> Unit = {},
    onClick: (id: Long) -> Unit = {}
) {
    val permissionState = rememberPermissionState(
        showMessage = viewModel::showMessage,
        onPermissionGranted = { onGranted() }
    )

    LaunchedEffect(Unit) { permissionState.launch() }

    with(viewModel.state.collectAsStateWithLifecycle().value) {
        LandingContent(
            isLoading = isLoading,
            message = message,
            content = data,
            onRefresh = viewModel::onRefresh
        ) { onClick(it) }
    }
}

@Composable
fun LandingContent(
    isLoading: Boolean,
    message: String,
    content: List<Content>,
    contentPadding: PaddingValues = PaddingValues(2.dp),
    onRefresh: () -> Unit,
    onClick: (id: Long) -> Unit,
) {
    BaseScreen(
        isLoading = isLoading,
        topBar = { BasicTopBar(title = "Aptoide", navigationIconEnable = false) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = message)
            Button(onClick = onRefresh) {
                Text(text = "Refresh")
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = contentPadding,
            ) {
                itemsIndexed(items = content, key = { index, item -> "${item.id}" }) { _, item ->
                    AppCard(
                        photo = item.icon,
                        title = item.name,
                        rating = item.rating.toString(),
                    ) { onClick(item.id) }
                }
            }
        }
    }
}
