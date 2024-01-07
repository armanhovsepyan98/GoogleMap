package com.app.composemaps.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.composemaps.data.model.LocationEntity

@Database(
    entities = [LocationEntity::class],
    version = 1
)
abstract class LocationDatabase : RoomDatabase() {

    abstract val dao: LocationDao
}