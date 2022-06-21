package com.vadimulasevich.myweather.ui.screen.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vadimulasevich.myweather.databinding.ItemWeatherSearchScreenBinding
import com.vadimulasevich.myweather.db.local.models.Weather

class WeatherListRecyclerDiffAdapter(
    private val layoutInflater: LayoutInflater
) : ListAdapter<Weather, WeatherListRecyclerDiffAdapter.MyViewHolder>(DIFF_CALLBACK) {

    fun setData(weather: List<Weather>) {
        submitList(weather)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        val binding = ItemWeatherSearchScreenBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        Log.d("getWeather", "onBindViewHolder")

        holder.binding.apply {
            Log.d("getWeather", "holder.binding.apply")

            itemWeatherDayAndTime.text = item.dateTime
            itemWeatherTempretureDay.text = "${item.tempreture!!.toInt()} °C"
            itemWeatherPrecipitationProbability.text = "Precipitation probability: ${item.precipitationProbability}"
            itemWeatherVisibility.text = "Visibility: ${item.visibility} м"
            itemWeatherCloudy.text = "Cloudy: ${item.clouds} %"
        }
    }

    inner class MyViewHolder(val binding: ItemWeatherSearchScreenBinding) :
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