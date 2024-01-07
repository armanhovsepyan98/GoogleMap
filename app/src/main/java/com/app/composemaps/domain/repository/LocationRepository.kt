package com.app.composemaps.domain.repository

import com.app.composemaps.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun insertLocation(location: Location)

    suspend fun deleteAllLocations(locations: List<Location>)

    fun getLocations(): Flow<List<Location>>
}