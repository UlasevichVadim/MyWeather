package com.vadimulasevich.myweather.db.repositories.local

import com.vadimulasevich.myweather.db.local.dao.WeatherDao
import com.vadimulasevich.myweather.db.local.models.Weather
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