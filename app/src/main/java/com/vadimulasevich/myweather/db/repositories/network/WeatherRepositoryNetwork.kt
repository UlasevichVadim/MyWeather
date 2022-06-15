package com.vadimulasevich.myweather.db.repositories.network

import com.vadimulasevich.myweather.db.network.api.WeatherApi
import com.vadimulasevich.myweather.db.network.models.ReqresWeatherApiResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

import retrofit2.Callback

class WeatherRepositoryNetwork(private val weatherApi: WeatherApi): KoinComponent {

    private val koinWeatherApi by inject<WeatherApi>()

    fun getWeatherOneTownOneDay(callback: Callback<ReqresWeatherApiResponse>){
        koinWeatherApi.getWeatherOneTownOneDay().enqueue(callback)
    }

    fun getWeatherAllNextWeek(callback: Callback<ReqresWeatherApiResponse>){
        weatherApi.getWeatherAllNextWeek().enqueue(callback)
    }
}