package com.vadimulasevich.myweather.network.modelsOneDay

data class WeatherNet(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)