package com.vadimulasevich.myweather.network.modelsForecast

data class WeatherForecast(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)