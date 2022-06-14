package com.vadimulasevich.yourweather.db.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getWeather(): LiveData<List<Weather>>

    @Insert
    fun insertWeather(weather: Weather)

}