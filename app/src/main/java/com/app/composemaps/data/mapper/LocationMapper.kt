package com.app.composemaps.data.mapper

import com.app.composemaps.data.model.LocationEntity
import com.app.composemaps.domain.model.Location

fun LocationEntity.toLocation(): Location {
    return Location(
        lat = lat,
        lng = lng,
        id = id,
    )
}

fun Location.toLocationEntity(): LocationEntity {
    return LocationEntity(
        lat = lat,
        lng = lng,
        id = id
    )
}