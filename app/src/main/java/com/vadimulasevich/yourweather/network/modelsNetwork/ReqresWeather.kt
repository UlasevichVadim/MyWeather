package com.vadimulasevich.yourweather.network.modelsNetwork

data class ReqresWeather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)