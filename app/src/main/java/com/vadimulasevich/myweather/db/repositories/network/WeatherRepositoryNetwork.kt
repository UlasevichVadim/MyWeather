package com.vadimulasevich.myweather.db.repositories.network

import com.vadimulasevich.myweather.db.network.api.WeatherApi
import com.vadimulasevich.myweather.db.network.modelsForecast.ReceivedWeatherApiForecastResponse
import com.vadimulasevich.myweather.db.network.modelsOneDay.ReceivedWeatherApiResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

import retrofit2.Callback

class WeatherRepositoryNetwork(private val weatherApi: WeatherApi) : KoinComponent {

    private val koinWeatherApi by inject<WeatherApi>()

    fun getWeather(
        lat: Double,
        lon: Double,
        callback: Callback<ReceivedWeatherApiResponse>,
    ) {
        koinWeatherApi.getWeather(
            lat,
            lon
        ).enqueue(callback)
    }

    fun getWeatherForecast(
        cityName: String,
        callback: Callback<ReceivedWeatherApiForecastResponse>,
    ) {
        weatherApi.getWeatherForecast(
            cityName
        ).enqueue(callback)
    }
}