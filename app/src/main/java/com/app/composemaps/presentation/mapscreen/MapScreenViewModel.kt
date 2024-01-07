package com.app.composemaps.presentation.mapscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composemaps.domain.model.Location
import com.app.composemaps.domain.repository.LocationRepository
import com.app.composemaps.utils.LineType
import com.app.composemaps.utils.MapStyle
import com.app.composemaps.utils.ThemeOption
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    var state by mutableStateOf(MapState())

    init {
        viewModelScope.launch {
            delay(5000)
            repository.getLocations().collectLatest { location ->
                state = state.copy(
                    locations = location
                )
            }
        }
    }

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.OnMapClick -> {
                viewModelScope.launch {
                    repository.insertLocation(
                        Location(
                            event.latLng.latitude,
                            event.latLng.longitude,
                        )
                    )
                }
            }

            MapEvent.DeleteAllLocations -> {
                viewModelScope.launch {
                    repository.deleteAllLocations(
                        state.locations
                    )
                }
            }

            is MapEvent.SetMapTheme -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = when (event.type) {
                            ThemeOption.Normal -> {
                                null
                            }

                            ThemeOption.Dark -> {
                                MapStyleOptions(MapStyle.mapJsonDark)
                            }

                            ThemeOption.Winter -> {
                                MapStyleOptions(MapStyle.mapJsonWinter)

                            }
                        }
                    )
                )
            }

            is MapEvent.SetMapLineType -> {
                state = state.copy(
                    lineType = when (event.type) {
                        LineType.None -> LineType.None
                        LineType.Polyline -> LineType.Polyline
                        LineType.Polygon -> LineType.Polygon
                    }
                )
            }
        }
    }
}


data class MapState(
    val properties: MapProperties = MapProperties(),
    val locations: List<Location> = emptyList(),
    val lineType: LineType = LineType.None
)

sealed class MapEvent {
    data class SetMapTheme(val type: ThemeOption) : MapEvent()
    data class SetMapLineType(val type: LineType) : MapEvent()
    data class OnMapClick(val latLng: LatLng) : MapEvent()
    object DeleteAllLocations : MapEvent()
}