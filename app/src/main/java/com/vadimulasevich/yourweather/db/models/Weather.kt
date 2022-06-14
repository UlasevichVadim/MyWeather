package com.vadimulasevich.yourweather.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Weather(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "tempreture") val tempreture: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "address") val address: String,
)