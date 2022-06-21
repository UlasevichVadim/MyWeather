package com.vadimulasevich.myweather.network.modelsForecast

data class CityForecast(
    val coord: CoordForecast,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)