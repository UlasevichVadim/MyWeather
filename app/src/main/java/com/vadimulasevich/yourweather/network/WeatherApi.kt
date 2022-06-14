package com.vadimulasevich.yourweather.network

import com.vadimulasevich.yourweather.API_KEY
import com.vadimulasevich.yourweather.CITY
import com.vadimulasevich.yourweather.network.modelsNetworkMainScreen.ReqresWeatherApiResponse
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

    //https://api.openweathermap.org/data/2.5/onecall?lat=53.89&lon=27.56&exclude=minutely,
// hourly, daily&&appid=a62634e963351b2cc4757baa1b9f3dd7&units=metric
    //где: exclude
    //С помощью этого параметра вы можете исключить некоторые части данных о погоде из ответа API. Это должен быть список, разделенный запятыми (без пробелов).
//Доступные значения:
    //
    //current - текущая
    //minutely - по минутам
    //hourly - по часам
    //daily  - по дням
    //alerts - оповещения
}