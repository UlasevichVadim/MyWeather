package com.vadimulasevich.myweather.network.modelsForecast

data class ReceivedWeatherApiForecastResponse(
    val city: CityForecast,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)