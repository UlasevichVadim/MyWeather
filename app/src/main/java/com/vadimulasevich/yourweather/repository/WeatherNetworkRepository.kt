package com.vadimulasevich.yourweather.repository

import com.vadimulasevich.yourweather.network.WeatherApi
import com.vadimulasevich.yourweather.network.modelsNetwork.WeatherApiResponse
import retrofit2.Callback

class WeatherNetworkRepository(private val weatherApi: WeatherApi) {
    fun getWeather(callback: Callback<WeatherApiResponse>){
        weatherApi.getWeather().enqueue(callback)
    }
}