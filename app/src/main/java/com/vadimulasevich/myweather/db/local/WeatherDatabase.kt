package com.vadimulasevich.myweather.db.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vadimulasevich.myweather.db.local.dao.WeatherDao
import com.vadimulasevich.myweather.db.local.models.Weather

@Database(entities = [Weather::class], version = 1 )
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}