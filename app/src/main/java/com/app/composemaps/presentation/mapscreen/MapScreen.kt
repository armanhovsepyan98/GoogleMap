package com.app.composemaps.presentation.mapscreen


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.composemaps.utils.LineType.*
import com.app.composemaps.utils.ThemeOption
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(viewModel: MapScreenViewModel = hiltViewModel()) {

    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            onMapLongClick = {
                viewModel.onEvent(MapEvent.OnMapClick(it))
            },
        ) {
            viewModel.state.locations.forEach { location ->
                Marker(
                    state = MarkerState(position = LatLng(location.lat, location.lng)),
                    title = "Coordinate (${location.lat}, ${location.lng})",
                    draggable = true,
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_AZURE
                    )
                )
            }

            when (viewModel.state.lineType) {
                None -> {}
                Polyline -> {
                    Polyline(points = viewModel.state.locations.map { location ->
                        LatLng(location.lat, location.lng)
                    }, color = Color.Red, width = 10f)
                }

                Polygon -> {
                    Polygon(
                        points = viewModel.state.locations.map { location ->
                            LatLng(location.lat, location.lng)
                        },
                        fillColor = Color.Blue,
                        strokeColor = Color.Red,
                        strokeWidth = 2F
                    )
                }
            }
        }

        BottomButtons(
            modifier = Modifier.align(Alignment.BottomCenter),
            onNormalClick = {
                viewModel.onEvent(MapEvent.SetMapTheme(ThemeOption.Normal))
            },
            onDarkClick = {
                viewModel.onEvent(MapEvent.SetMapTheme(ThemeOption.Dark))
            },
            onWinterClick = {
                viewModel.onEvent(MapEvent.SetMapTheme(ThemeOption.Winter))
            },
            onNoneClick = {
                viewModel.onEvent(MapEvent.SetMapLineType(None))
            },
            onPolygonClick = {
                viewModel.onEvent(MapEvent.SetMapLineType(Polygon))
            },
            onPolylineClick = {
                viewModel.onEvent(MapEvent.SetMapLineType(Polyline))
            },
            onClearLocationsClick = {
                viewModel.onEvent(MapEvent.DeleteAllLocations)
            },
        )
    }
}

@Composable
fun BottomButtons(
    modifier: Modifier,
    onNormalClick: () -> Unit,
    onDarkClick: () -> Unit,
    onWinterClick: () -> Unit,
    onNoneClick: () -> Unit,
    onPolygonClick: () -> Unit,
    onPolylineClick: () -> Unit,
    onClearLocationsClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        AppButton("Clear Locations") { onClearLocationsClick() }
        Row(
            modifier = modifier.padding(10.dp),
        ) {
            AppButton("Normal") { onNormalClick() }
            AppButton("Dark") { onDarkClick() }
            AppButton("Winter") { onWinterClick() }

        }
        Row(
            modifier = modifier.padding(10.dp),
        ) {
            AppButton("None") { onNoneClick() }
            AppButton("Polygon") { onPolygonClick() }
            AppButton("Polyline") { onPolylineClick() }
        }
    }
}

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
    ) {
        Text(text = text)
    }
}
