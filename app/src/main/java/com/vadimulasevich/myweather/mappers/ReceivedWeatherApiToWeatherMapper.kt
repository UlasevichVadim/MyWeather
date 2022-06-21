package com.vadimulasevich.myweather.mappers

import com.vadimulasevich.myweather.db.local.models.Weather
import com.vadimulasevich.myweather.network.modelsForecast.Forecast
import com.vadimulasevich.myweather.network.modelsForecast.ReceivedWeatherApiForecastResponse
import com.vadimulasevich.myweather.network.modelsOneDay.ReceivedWeatherApiResponse

class ReceivedWeatherApiToWeatherMapper {

    fun toWeather(apiResponse: ReceivedWeatherApiResponse) = Weather(
        cityName = apiResponse.name,
        countryName = apiResponse.sys.country,
        updateDate = apiResponse.dt,
        tempreture = apiResponse.main.temp,
        tempretureMax = apiResponse.main.temp_max,
        tempretureMin = apiResponse.main.temp_min,
        sunrise = apiResponse.sys.sunrise,
        sunset = apiResponse.sys.sunset,
        wind = apiResponse.wind.speed,
        pressure = apiResponse.main.pressure,
        humidity = apiResponse.main.humidity,
        clouds = apiResponse.clouds.all
    )

    fun toForecastList(apiResponse: ReceivedWeatherApiForecastResponse) = apiResponse.list.map {
        toWeatherSearch(it)
    }

    fun toWeatherSearch(forecast: Forecast) = Weather(
        dateTime = forecast.dt_txt,
        tempreture = forecast.main.temp,
        precipitationProbability = forecast.pop,
        visibility = forecast.visibility,
        clouds = forecast.clouds.all
    )
}