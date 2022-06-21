package com.vadimulasevich.myweather.db.repositories.network

import com.vadimulasevich.myweather.network.api.WeatherApi
import com.vadimulasevich.myweather.network.modelsForecast.ReceivedWeatherApiForecastResponse
import com.vadimulasevich.myweather.network.modelsOneDay.ReceivedWeatherApiResponse
import retrofit2.Callback

class WeatherRepositoryNetwork(private val weatherApi: WeatherApi)  {

    fun getWeather(
        lat: Double,
        lon: Double,
        callback: Callback<ReceivedWeatherApiResponse>,
    ) {
        weatherApi.getWeather(
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