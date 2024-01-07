package com.app.composemaps.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun PermissionHandler(
    permissions: List<String>,
    onPermissionGranted: () -> Unit
) {
    val permissionState = remember { mutableStateOf<Map<String, Boolean>>(emptyMap()) }

    val launcher =
        rememberLauncherForActivityResult(RequestMultiplePermissions()) { result ->
            permissionState.value = result
            if (result.all { it.value }) {
                onPermissionGranted()
            } else {
                // Handle denied permissions
            }
        }

    LaunchedEffect(permissions) {
        launcher.launch(permissions.toTypedArray())
    }
}
