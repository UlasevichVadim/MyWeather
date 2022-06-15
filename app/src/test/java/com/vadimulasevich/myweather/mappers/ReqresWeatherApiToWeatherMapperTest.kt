package com.vadimulasevich.myweather.mappers

import com.vadimulasevich.myweather.db.models.Weather
import com.vadimulasevich.myweather.db.network.models.ReqresWeather
import org.junit.Assert.*

import org.junit.Test

class ReqresWeatherApiToWeatherMapperTest {


    val sut = ReqresWeatherApiToWeatherMapper()

    @Test
    fun toWeather() {

        val regresWeather = ReqresWeather(
            "description",
            "icon",
            1,
            "main"
        )
        val expected = Weather(
            1,
            "icon",
            "description",
            "main"
        )
        val actual = sut.toWeather(regresWeather)

        assertEquals(expected, actual)

    }


    @Test
    fun toWeatherList() {
    }
}