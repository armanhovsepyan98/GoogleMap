package com.app.composemaps.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.composemaps.data.model.LocationEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity)

    @Delete
    suspend fun deleteAllLocations(locations: List<LocationEntity>)

    @Query("SELECT * FROM locationentity")
    fun getLocations(): Flow<List<LocationEntity>>
}