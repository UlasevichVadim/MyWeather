package com.vadimulasevich.myweather.ui.screen.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.vadimulasevich.myweather.R
import com.vadimulasevich.myweather.databinding.FragmentSearchScreenBinding
import com.vadimulasevich.myweather.utils.ResultState
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class SearchScreenFragment : Fragment(R.layout.fragment_search_screen), KoinComponent {

    private var _binding: FragmentSearchScreenBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapter: WeatherListRecyclerDiffAdapter

    private val viewModel: SearchScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = WeatherListRecyclerDiffAdapter(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchScreenBinding.bind(view).apply {
            localWeatherRecyclerView.adapter = adapter
            localWeatherRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            searchButton.setOnClickListener {
                viewModel.searchWeather(binding
                    .searchWeatherEditText
                    .text
                    .toString())
            }
        }
        viewModel.localWeatherWeekList.observe(viewLifecycleOwner) {
            when (it) {
                is ResultState.Error -> {
                    binding.progressBarSearchScreen.visibility = View.VISIBLE
                    binding.localWeatherRecyclerView.visibility = View.GONE
                    binding.showMessageErrorOnSearchScreen.text = "Error: ${it.throwable.message}"
                    Log.e("SearchScreenFragment", it.throwable.stackTraceToString())
                }
                is ResultState.Loading -> {
                    binding.progressBarSearchScreen.visibility = View.VISIBLE
                    binding.localWeatherRecyclerView.visibility = View.GONE
                    binding.showMessageErrorOnSearchScreen.text = "Loading..."
                }
                is ResultState.Success -> {
                    binding.progressBarSearchScreen.visibility = View.GONE
                    binding.localWeatherRecyclerView.visibility = View.VISIBLE
                    adapter.setData(it.data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}