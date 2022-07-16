package com.vadimulasevich.myweather.db.repositories.local

import com.google.gson.GsonBuilder
import com.vadimulasevich.myweather.db.local.models.LocationUser
import java.io.File

class LocationRepository {

    private var _locationUser = mutableListOf<LocationUser>()
    var locationUser: List<LocationUser> = _locationUser
        private set

    private val gson = GsonBuilder().apply {
        setPrettyPrinting()
    }.create()


    fun saveLocation() {
        val file = File("", "usersLocation.json")
        file.bufferedWriter().use {
            gson.toJson(_locationUser, it)
            locationUser = _locationUser
        }
    }

    fun loadLocation() {
        val file = File("", "usersLocation.json")
        if (file.exists()) {
            file.bufferedReader().use {
                val userArray = gson.fromJson(it, Array<LocationUser>::class.java)
                _locationUser = userArray.toMutableList()
                locationUser = _locationUser
            }
        }
    }

    fun setLocation(latitude: Double, longitude: Double) {
        val loc = LocationUser(latitude = latitude, longitude = longitude)
        _locationUser.add(loc)
        saveLocation()
    }

    fun getLocation(): LocationUser {
        loadLocation()
        return locationUser[0]
    }
}