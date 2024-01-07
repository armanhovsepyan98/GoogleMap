package com.app.composemaps.data.repository

import com.app.composemaps.data.database.LocationDao
import com.app.composemaps.data.mapper.toLocation
import com.app.composemaps.data.mapper.toLocationEntity
import com.app.composemaps.domain.model.Location
import com.app.composemaps.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationRepositoryImpl(
    private val dao: LocationDao
) : LocationRepository {

    override suspend fun insertLocation(location: Location) {
        dao.insertLocation(location.toLocationEntity())
    }

    override suspend fun deleteAllLocations(locations: List<Location>) {
        dao.deleteAllLocations(locations.map { location -> location.toLocationEntity() })
    }

    override fun getLocations(): Flow<List<Location>> {
        return dao.getLocations().map { location ->
            location.map { it.toLocation() }
        }
    }
}