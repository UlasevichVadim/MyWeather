package com.vadimulasevich.myweather.ui.screen.main

import android.annotation.SuppressLint
import android.util.Log
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
    private val layoutInflater: LayoutInflater,
    private val clickListener: WeatherClickListener,
) : ListAdapter<Weather, WeatherRecyclerDiffAdapter.MyViewHolder>(DIFF_CALLBACK) {


//    private val weatherList = ArrayList<Weather>()


    fun setData(weather: Weather) {

        val listWeather = listOf(weather)
        submitList(listWeather)

//        weatherList.clear()
//        weatherList.addAll(listWeather)
//        notifyDataSetChanged()
//        Log.d("getWeather", "setData")
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        val binding = ItemWeatherMainScreenBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("getWeather", "onBindViewHolder")
//        holder.bindItems(weatherList[position])
        val item = getItem(position)

        holder.binding.apply {
            Log.d("getWeather", "holder.binding.apply")
            address.text = "${item.cityName} + ${item.countryName}"
            updatedAt.text =
                "Update at: " + SimpleDateFormat("dd/MM/yyyy HH:mm a", Locale.ENGLISH).format(Date(
                    item.updateDate!!.toLong()))
            temp.text = "${item.tempreture}"
            tempMax.text = "${item.tempretureMax}"
            tempMin.text = "${item.tempretureMin}"
            sunrise.text =
                SimpleDateFormat("HH:mm a", Locale.ENGLISH).format(Date(item.sunrise!!.toLong()))
            sunset.text =
                SimpleDateFormat("HH:mm a", Locale.ENGLISH).format(Date(item.sunset!!.toLong()))
            wind.text = "${item.wind}"
            pressure.text = "${item.pressure}"
            humidity.text = "${item.humidity}"
            clouds.text = "${item.clouds}"
        }
    }

    inner class MyViewHolder(val binding: ItemWeatherMainScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val weather = getItem(adapterPosition)
                    clickListener.onUserClicked(weather)
                }
            }
        }

//        fun     bindItems(weather: Weather) {
//            binding.apply {
//                Log.d("getWeather", "bindItems")
//
//                address.text = "${weather.cityName} + ${weather.countryName}"
//                updatedAt.text =
//                    "Update at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(
//                        weather.updateDate!!.toLong()))
//                temp.text = "${weather.tempreture}"
//                tempMax.text = "${weather.tempretureMax}"
//                tempMin.text = "${weather.tempretureMin}"
//                sunrise.text =
//                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(weather.sunrise!!.toLong()))
//                sunset.text =
//                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(weather.sunset!!.toLong()))
//                wind.text = "${weather.wind}"
//                pressure.text = "${weather.pressure}"
//                humidity.text = "${weather.humidity}"
//                clouds.text = "${weather.clouds}"
//            }
//        }
    }

    interface WeatherClickListener {
        fun onUserClicked(weather: Weather)
    }

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