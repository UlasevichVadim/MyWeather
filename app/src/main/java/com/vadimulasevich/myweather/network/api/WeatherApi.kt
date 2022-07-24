package com.vadimulasevich.myweather.network.api

import com.vadimulasevich.myweather.network.modelsForecast.ReceivedWeatherApiForecastResponse
import com.vadimulasevich.myweather.network.modelsOneDay.ReceivedWeatherApiResponse
import com.vadimulasevich.myweather.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = Constants.WEATHER_UNIT,
        @Query("appid") appid: String = Constants.API_KEY
    ): Call<ReceivedWeatherApiResponse>

    @GET("forecast")
    fun getWeatherForecast(
        @Query("q") q: String,
        @Query("units") units: String = Constants.WEATHER_UNIT,
        @Query("appid") appid: String = Constants.API_KEY
    ): Call<ReceivedWeatherApiForecastResponse>
}