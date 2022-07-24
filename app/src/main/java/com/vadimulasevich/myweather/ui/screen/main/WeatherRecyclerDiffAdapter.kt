package com.vadimulasevich.myweather.ui.screen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vadimulasevich.myweather.databinding.ItemWeatherMainScreenBinding
import com.vadimulasevich.myweather.db.local.models.Weather
import java.text.SimpleDateFormat
import java.util.*

class WeatherRecyclerDiffAdapter(
    private val layoutInflater: LayoutInflater
) : ListAdapter<Weather, WeatherRecyclerDiffAdapter.MyViewHolder>(DIFF_CALLBACK) {

    fun setData(weather: Weather) {
        val listWeather = listOf(weather)
        submitList(listWeather)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        val binding = ItemWeatherMainScreenBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            address.text = "${item.cityName}, ${item.countryName}"
            updatedAtNumber.text = "" + SimpleDateFormat("dd/MM/yyyy HH:mm:ss a").format(Date(item.updateDate!!*1000L))
            temp.text = "${item.tempreture!!.toInt()} °C"
            tempMaxNumber.text = " ${item.tempretureMax!!.toInt()} °C"
            tempMinNumber.text = " ${item.tempretureMin!!.toInt()} °C"
            sunrise.text =
                SimpleDateFormat("HH:mm a").format(Date(item.sunrise!!*1000L))
            sunset.text =
                SimpleDateFormat("HH:mm a").format(Date(item.sunset!!*1000L))
            wind.text = "${item.wind} m/s"
            pressure?.text = "${item.pressure} hPa"
            humidity?.text = "${item.humidity} %"
            clouds?.text = "${item.clouds} %"
        }
    }

    inner class MyViewHolder(val binding: ItemWeatherMainScreenBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Weather> =
            object : DiffUtil.ItemCallback<Weather>() {
                override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                    return oldItem.id == newItem.id
                }
                override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                    return oldItem == newItem
                }
            }
    }
}