package com.vadimulasevich.yourweather.repository

import com.vadimulasevich.yourweather.db.models.Weather
import com.vadimulasevich.yourweather.db.models.WeatherDao
import java.util.concurrent.Executor


class WeatherRepositoryDb(
    private val weatherDao: WeatherDao,
    private val ioExecutor: Executor
){


    fun getWeather() = weatherDao.getWeather()

    fun addWeather(weather: Weather) {
        ioExecutor.execute {
            weatherDao.insertWeather(weather)
        }
    }

}