package com.vadimulasevich.myweather.network.modelsForecast

data class WindForecast(
    val deg: Int,
    val gust: Double,
    val speed: Double
)