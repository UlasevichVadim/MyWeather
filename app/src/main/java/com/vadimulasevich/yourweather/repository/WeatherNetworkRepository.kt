package com.vadimulasevich.yourweather.repository

import com.vadimulasevich.yourweather.network.WeatherApi
import com.vadimulasevich.yourweather.network.modelsNetworkMainScreen.ReqresWeatherApiResponse
import retrofit2.Callback

class WeatherNetworkRepository(private val weatherApi: WeatherApi) {
    fun getWeatherOneTownOneDay(callback: Callback<ReqresWeatherApiResponse>){
        weatherApi.getWeatherOneTownOneDay().enqueue(callback)
    }

    fun getWeatherAllNextWeek(callback: Callback<ReqresWeatherApiResponse>){
        weatherApi.getWeatherAllNextWeek().enqueue(callback)
    }
}