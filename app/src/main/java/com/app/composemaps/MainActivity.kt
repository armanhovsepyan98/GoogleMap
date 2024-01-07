package com.app.composemaps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.app.composemaps.presentation.mapscreen.MapScreen
import com.app.composemaps.presentation.permisionscreen.PermissionScreen
import com.app.composemaps.utils.checkForPermission
import com.app.composemaps.ui.theme.GoogleMapJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleMapJetpackComposeTheme {
                var hasLocationPermission by remember {
                    mutableStateOf(checkForPermission(this))
                }
                if (hasLocationPermission) {
                    MapScreen()
                } else {
                    PermissionScreen {
                        hasLocationPermission = true
                    }
                }
            }
        }
    }
}

