package com.vadimulasevich.myweather.db.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vadimulasevich.myweather.db.local.models.Weather


@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getWeather(): LiveData<List<Weather>>

    @Insert
    fun insertWeather(weather: Weather)

}