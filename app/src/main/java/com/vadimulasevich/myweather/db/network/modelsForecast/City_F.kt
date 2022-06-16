package com.vadimulasevich.myweather.db.network.modelsForecast

data class City_F(
    val coord: Coord_F,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)