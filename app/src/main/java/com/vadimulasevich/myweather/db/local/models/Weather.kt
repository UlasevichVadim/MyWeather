package com.vadimulasevich.myweather.db.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "tempreture") var tempreture: Double? = null,
    @ColumnInfo(name = "cityName") var cityName: String? = null,
    @ColumnInfo(name = "countryName") var countryName: String? = null,
    @ColumnInfo(name = "dateTime") var dateTime: String? = null,
    @ColumnInfo(name = "updateDate") var updateDate: Int? = null,
    @ColumnInfo(name = "tempretureMax") var tempretureMax: Double? = null,
    @ColumnInfo(name = "clouds") var clouds: Int? = null,
    @ColumnInfo(name = "tempretureMin") var tempretureMin: Double? = null,
    @ColumnInfo(name = "precipitationProbability") var precipitationProbability: Double? = null,
    @ColumnInfo(name = "visibility") var visibility: Int? = null,
    @ColumnInfo(name = "sunset") var sunset: Int? = null,
    @ColumnInfo(name = "sunrise") var sunrise: Int? = null,
    @ColumnInfo(name = "wind") var wind: Double? = null,
    @ColumnInfo(name = "pressure") var pressure: Int? = null,
    @ColumnInfo(name = "humidity") var humidity: Int? = null
)