package com.vadimulasevich.myweather.db.network.modelsForecast

data class ReceivedWeatherApiForecastResponse(
    val city: City_F,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)