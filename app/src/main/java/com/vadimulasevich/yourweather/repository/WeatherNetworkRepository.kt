package com.vadimulasevich.yourweather.repository

import com.vadimulasevich.yourweather.network.WeatherApi
import com.vadimulasevich.yourweather.network.modelsNetworkMainScreen.ReqresWeatherApiResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

import retrofit2.Callback

class WeatherNetworkRepository(private val weatherApi: WeatherApi): KoinComponent {

    private val koinWeatherApi by inject<WeatherApi>()

    fun getWeatherOneTownOneDay(callback: Callback<ReqresWeatherApiResponse>){
//        weatherApi.getWeatherOneTownOneDay().enqueue(callback)

        koinWeatherApi.getWeatherOneTownOneDay().enqueue(callback)
    }

    fun getWeatherAllNextWeek(callback: Callback<ReqresWeatherApiResponse>){
        weatherApi.getWeatherAllNextWeek().enqueue(callback)
    }
}