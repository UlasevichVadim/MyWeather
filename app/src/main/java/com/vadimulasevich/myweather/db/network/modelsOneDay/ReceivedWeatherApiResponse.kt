package com.vadimulasevich.myweather.db.network.modelsOneDay

data class ReceivedWeatherApiResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weatherNet: List<WeatherNet>,
    val wind: Wind
)