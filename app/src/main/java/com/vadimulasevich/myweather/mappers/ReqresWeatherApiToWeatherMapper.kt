package com.vadimulasevich.myweather.mappers

import com.vadimulasevich.myweather.db.local.models.Weather
import com.vadimulasevich.myweather.db.network.models.ReqresWeather
import com.vadimulasevich.myweather.db.network.models.ReqresWeatherApiResponse

class ReqresWeatherApiToWeatherMapper {

    fun toWeather(reqresWeather: ReqresWeather) = Weather(
        id = reqresWeather.id,
        tempreture = reqresWeather.main,
        description = reqresWeather.icon,
        address = reqresWeather.description
    )

    fun toWeatherList(apiResponse: ReqresWeatherApiResponse) = apiResponse.weather.map{
        toWeather(it)
    }
}