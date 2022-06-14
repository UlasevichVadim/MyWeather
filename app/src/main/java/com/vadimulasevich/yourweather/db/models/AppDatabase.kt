package com.vadimulasevich.yourweather.db.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Weather::class], version = 1 )
abstract class AppDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao


    companion object{
        lateinit var instance: AppDatabase

        fun init(applicationContext: Context){
            instance = Room
                .databaseBuilder(applicationContext, AppDatabase::class.java, "db")
                .build()

        }
    }
}