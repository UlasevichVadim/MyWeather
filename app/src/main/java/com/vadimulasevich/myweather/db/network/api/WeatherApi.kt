package com.vadimulasevich.myweather.db.network.api

import com.vadimulasevich.myweather.API_KEY
import com.vadimulasevich.myweather.CITY
import com.vadimulasevich.myweather.db.network.models.ReqresWeatherApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {

    @GET("weather?q=$CITY&units=metric&appid=$API_KEY")
    fun getWeatherOneTownOneDay(): Call<ReqresWeatherApiResponse>

    @GET("weather?q=$CITY&units=metric&appid=$API_KEY")
    fun getWeatherAllNextWeek(): Call<ReqresWeatherApiResponse>

    companion object {
        var BASE_URL = "https://api.openweathermap.org/data/2.5/"

    }
}

//http://api.weatherstack.com/current?access_key=7ae10cf39ac55bff8e3b1b1882e0f00f&query=Minsk