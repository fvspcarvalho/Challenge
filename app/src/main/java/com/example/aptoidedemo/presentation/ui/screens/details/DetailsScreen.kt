package com.example.aptoidedemo.presentation.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aptoidedemo.core.models.local.Content
import com.example.aptoidedemo.presentation.ui.resources.BaseScreen
import com.example.aptoidedemo.presentation.ui.resources.BasicTopBar
import com.example.aptoidedemo.presentation.ui.resources.DetailsCard
import com.example.aptoidedemo.presentation.ui.resources.MyAlertDialog

@Composable
fun DetailsScreen(
    id: Long,
    viewModel: DetailsViewModel = hiltViewModel(),
    onBack: () -> Unit = {}
) {
    with(viewModel.state.collectAsStateWithLifecycle().value) {
        DetailsContent(
            content = content,
            showDialog = showDialog,
            onClick = { viewModel.onClick(true) },
            onDismiss = { viewModel.onClick(false) }
        ) { onBack() }
    }
    LaunchedEffect(Unit) { viewModel.getAppName(id) }
}

@Composable
fun DetailsContent(
    content: Content,
    showDialog: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onBack: () -> Unit,
) {
    with(content) {
        if (showDialog) {
            MyAlertDialog(
                title = name,
                onDismiss = onDismiss,
            )
        }

        BaseScreen(
            topBar = { BasicTopBar(title = content.name) { onBack() } }
        ) {
            Column {
                DetailsCard(
                    icon = icon,
                    photo = graphic,
                    downloads = downloads,
                    name = name,
                    storeName = storeName,
                    rating = rating.toString()
                ) {
                    onClick()
                }
            }
        }
    }

}