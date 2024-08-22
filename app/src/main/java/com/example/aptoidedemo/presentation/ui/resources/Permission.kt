package com.example.aptoidedemo.presentation.ui.resources

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

@Composable
fun rememberPermissionState(
    showMessage: () -> Unit,
    onPermissionGranted: () -> Unit
): PermissionState {
    val context = LocalContext.current
    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.POST_NOTIFICATIONS
    } else {
        ""
    }

    val checkPermission: () -> Boolean = {
        ContextCompat.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED
    }

    // rememberUpdatedState to ensure latest functions are used inside the launcher callback
    val currentShowMessage by rememberUpdatedState(showMessage)
    val currentOnPermissionGranted by rememberUpdatedState(onPermissionGranted)

    // Create the launcher using rememberLauncherForActivityResult
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                currentOnPermissionGranted()
            } else {
                currentShowMessage()
            }
        }
    )

    val requestPermission: () -> Unit = {
        if (permission.isNotEmpty()) {
            requestPermissionLauncher.launch(permission)
        } else {
            currentOnPermissionGranted()
        }
    }

    return remember {
        PermissionState(
            permission = permission,
            checkPermission = checkPermission,
            requestPermission = requestPermission
        )
    }
}

data class PermissionState(
    val permission: String,
    val checkPermission: () -> Boolean,
    val requestPermission: () -> Unit
) {
    fun launch() {
        if (!checkPermission()) {
            requestPermission()
        }
    }
}