package com.vadimulasevich.yourweather.network.models

data class WeatherNetwork(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)