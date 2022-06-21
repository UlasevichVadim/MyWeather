package com.vadimulasevich.myweather.ui.screen.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vadimulasevich.myweather.R
import com.vadimulasevich.myweather.utils.ResultState
import com.vadimulasevich.myweather.databinding.FragmentMainScreenBinding
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
        Log.d("getWeather", "onCreate")
        adapter = WeatherRecyclerDiffAdapter(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainScreenBinding.bind(view).apply {
            weatherRecyclerView?.adapter = adapter
            weatherRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        }
        Log.d("getWeather", "onViewCreated")



        viewModel.localWeatherList.observe(viewLifecycleOwner) {
            when (it) {
                is ResultState.Error -> {
                    Log.d("getWeather", "Error")
                    binding.progressBarMainScreen.visibility = View.VISIBLE
                    binding.weatherRecyclerView?.visibility = View.GONE
                    binding.showMessageErrorOnMainScreen.text = "Error: ${it.throwable.message}"
                    Log.e("SearchScreenFragment", it.throwable.stackTraceToString())
                }
                is ResultState.Loading -> {
                    Log.d("getWeather", "Loading")
                    binding.progressBarMainScreen.visibility = View.VISIBLE
                    binding.weatherRecyclerView?.visibility = View.GONE
                    binding.showMessageErrorOnMainScreen.text = "Loading..."
                }
                is ResultState.Success -> {
                    Log.d("getWeather", "Success")
                    binding.progressBarMainScreen.visibility = View.GONE
                    binding.weatherRecyclerView?.visibility = View.VISIBLE
                    binding.showMessageErrorOnMainScreen.visibility = View.GONE
                    adapter.setData(it.data)
                }
            }
        }
    }
}
