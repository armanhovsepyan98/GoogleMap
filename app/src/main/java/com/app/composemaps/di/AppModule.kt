package com.app.composemaps.di

import android.app.Application
import androidx.room.Room
import com.app.composemaps.data.database.LocationDatabase
import com.app.composemaps.data.repository.LocationRepositoryImpl
import com.app.composemaps.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLocationDatabase(app: Application): LocationDatabase {
        return Room.databaseBuilder(
            app,
            LocationDatabase::class.java,
            "location.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocationRepository(db: LocationDatabase): LocationRepository {
        return LocationRepositoryImpl(db.dao)
    }
}