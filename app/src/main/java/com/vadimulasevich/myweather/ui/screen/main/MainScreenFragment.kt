package com.vadimulasevich.myweather.ui.screen.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.vadimulasevich.myweather.R
import com.vadimulasevich.myweather.utils.ResultState
import com.vadimulasevich.myweather.databinding.FragmentMainScreenBinding
import com.vadimulasevich.myweather.db.local.models.Weather
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainScreenFragment : Fragment(R.layout.fragment_main_screen), KoinComponent {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding
        get() = _binding!!


    private lateinit var adapter: WeatherRecyclerDiffAdapter

    private val viewModel: MainScreenViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = WeatherRecyclerDiffAdapter(layoutInflater,
            object : WeatherRecyclerDiffAdapter.WeatherClickListener {
                override fun onUserClicked(weather: Weather) {
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainScreenBinding.bind(view)

        viewModel.localWeatherList.observe(viewLifecycleOwner) {
            when (it) {
                is ResultState.Error -> {
                    Log.d("getWeather", "Error")
                    binding.progressBarMainScreen.visibility = View.VISIBLE
                    binding.showMessageErrorOnMainScreen.text = "Error: ${it.throwable.message}"
                    Log.e("SearchScreenFragment", it.throwable.stackTraceToString())
                }
                is ResultState.Loading -> {
                    Log.d("getWeather", "Loading")
                    binding.progressBarMainScreen.visibility = View.VISIBLE
                    binding.showMessageErrorOnMainScreen.text = "Loading..."
                }
                is ResultState.Success -> {
                    Log.d("getWeather", "Success")
                    binding.progressBarMainScreen.visibility = View.GONE
                    binding.showMessageErrorOnMainScreen.visibility = View.GONE
                    adapter.setData(it.data)
                }
            }
        }
    }
}
