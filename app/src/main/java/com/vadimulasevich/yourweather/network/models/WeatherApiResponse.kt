package com.vadimulasevich.yourweather.network.models

data class WeatherApiResponse(
    val dt: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val weather: List<WeatherNetwork>,
    val wind: Wind
)