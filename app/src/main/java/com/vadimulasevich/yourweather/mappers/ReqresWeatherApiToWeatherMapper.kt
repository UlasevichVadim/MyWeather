package com.vadimulasevich.yourweather.mappers

import com.vadimulasevich.yourweather.db.models.Weather
import com.vadimulasevich.yourweather.network.modelsNetwork.ReqresWeather
import com.vadimulasevich.yourweather.network.modelsNetwork.ReqresWeatherApiResponse

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