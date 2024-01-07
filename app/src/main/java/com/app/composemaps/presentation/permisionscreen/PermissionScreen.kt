package com.app.composemaps.presentation.permisionscreen

import android.app.LocaleConfig
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.composemaps.presentation.PermissionHandler
import com.app.googlemapjetpackcompose.R

@Composable
fun PermissionScreen(hasLocationPermission: (Boolean) -> Unit) {
    val requiredPermissions = listOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.location),
            contentDescription = "Location Icon"
        )

        Text(
            text = "Please set permissions",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.clickable {
                openSettings(context)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        PermissionHandler(
            permissions = requiredPermissions,
            onPermissionGranted = {
                hasLocationPermission(true)
            }
        )
    }
}

private fun openSettings(context: android.content.Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = android.net.Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    context.startActivity(intent)
}