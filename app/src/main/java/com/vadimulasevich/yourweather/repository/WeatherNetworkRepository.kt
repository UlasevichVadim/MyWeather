package com.vadimulasevich.yourweather.repository

import com.vadimulasevich.yourweather.db.models.Weather
import com.vadimulasevich.yourweather.network.WeatherApi
import com.vadimulasevich.yourweather.network.models.WeatherApiResponse
import retrofit2.Callback

class WeatherNetworkRepository(private val weatherApi: WeatherApi) {
    fun getWeather(callback: Callback<WeatherApiResponse>){
        weatherApi.getWeather().enqueue(callback)
    }
}