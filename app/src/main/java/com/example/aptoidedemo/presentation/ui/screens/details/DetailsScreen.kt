package com.example.aptoidedemo.presentation.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aptoidedemo.core.models.local.Content
import com.example.aptoidedemo.presentation.ui.resources.BaseScreen
import com.example.aptoidedemo.presentation.ui.resources.BasicTopBar
import com.example.aptoidedemo.presentation.ui.resources.DetailsCard

@Composable
fun DetailsScreen(id: Long, modifier: Modifier = Modifier, viewModel: DetailsViewModel = hiltViewModel(), onBack: () -> Unit = {}) {
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
                    downloads = downloads.toString(),
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

@Composable
fun MyAlertDialog(
    title: String,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = { Text(text = "Download is not available in demo mode") },
        confirmButton = { // 6
            Button(onClick = onDismiss) {
                Text(
                    text = "Confirm",
                    color = Color.White
                )
            }
        }
    )
}