package com.app.composemaps.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationEntity(
    @PrimaryKey val id: Int? = null,
    val lat: Double,
    val lng: Double
)