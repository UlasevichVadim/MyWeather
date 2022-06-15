package com.vadimulasevich.myweather.db.network.models

data class ReqresWeather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)