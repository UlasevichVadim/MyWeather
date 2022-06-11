package com.vadimulasevich.yourweather.ui.screen.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vadimulasevich.yourweather.databinding.ItemWeatherSearchScreenBinding
import com.vadimulasevich.yourweather.db.models.Weather


///Пока, что будет Weather как шаблон модели!!!!!

class WeatherListRecyclerDiffAdapter(
    private val layoutInflater: LayoutInflater,
    private val clickListener: WeatherClickListener,
) : ListAdapter<Weather, WeatherListRecyclerDiffAdapter.MyViewHolder>(DIFF_CALLBACK) {

    fun setData(weather: List<Weather>) {
        submitList(weather.toMutableList())
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

        holder.binding.apply {

        }
    }

    inner class MyViewHolder(val binding: ItemWeatherSearchScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val weather = getItem(adapterPosition)
                    clickListener.onUserClicked(weather)
                }
            }
        }
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