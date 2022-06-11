package com.vadimulasevich.yourweather.network

import com.vadimulasevich.yourweather.API_KEY
import com.vadimulasevich.yourweather.CITY
import com.vadimulasevich.yourweather.network.models.WeatherApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WeatherApi {

    @GET("weather?q=$CITY&units=metric&appid=$API_KEY")
    fun getWeather(): Call<WeatherApiResponse>


    companion object{
        var BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun create(): WeatherApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(WeatherApi::class.java)
        }
    }
}