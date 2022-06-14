package com.vadimulasevich.yourweather.ui.screen.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vadimulasevich.yourweather.R
import com.vadimulasevich.yourweather.utils.ResultState
import com.vadimulasevich.yourweather.databinding.FragmentSearchScreenBinding
import com.vadimulasevich.yourweather.db.models.Weather
import com.vadimulasevich.yourweather.di.DependencyStorage
import com.vadimulasevich.yourweather.mappers.ReqresWeatherApiToWeatherMapper
import com.vadimulasevich.yourweather.network.WeatherApi
import com.vadimulasevich.yourweather.repository.WeatherNetworkRepository
import org.koin.core.component.KoinComponent


class SearchScreenFragment : Fragment(R.layout.fragment_search_screen), KoinComponent {

    private var _binding: FragmentSearchScreenBinding? = null
    private val binding
        get() = _binding!!


    private lateinit var adapter: WeatherListRecyclerDiffAdapter

    private lateinit var viewModel: SearchScreenViewModel

    private fun initViewModel() {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchScreenViewModel(
                    getKoin().get(),
                    getKoin().get()
                ) as T
            }
        }

        viewModel = ViewModelProvider(this, factory).get(SearchScreenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = WeatherListRecyclerDiffAdapter(layoutInflater,
            object : WeatherListRecyclerDiffAdapter.WeatherClickListener {
                override fun onUserClicked(weather: Weather) {
                    viewModel.onUserClicked(weather)
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        _binding = FragmentSearchScreenBinding.bind(view).apply {
            recyclerViewListLocalWeatherWeek.adapter = adapter
            recyclerViewListLocalWeatherWeek.layoutManager = LinearLayoutManager(requireContext())
        }



        viewModel.localWeatherWeekList.observe(viewLifecycleOwner) {
            when (it) {
                is ResultState.Error -> {
                    binding.progressBarSearchScreen.visibility = View.VISIBLE
                    binding.recyclerViewListLocalWeatherWeek.visibility = View.GONE
                    binding.showMessageErrorOnSearchScreen.text = "Error: " + it.throwable.message
                    Log.e("SearchScreenFragment", it.throwable.stackTraceToString())
                }
                is ResultState.Loading -> {
                    binding.progressBarSearchScreen.visibility = View.VISIBLE
                    binding.recyclerViewListLocalWeatherWeek.visibility = View.GONE
                    binding.showMessageErrorOnSearchScreen.text = "Loading..."
                }
                is ResultState.Success -> {
                    binding.progressBarSearchScreen.visibility = View.GONE
                    binding.recyclerViewListLocalWeatherWeek.visibility = View.VISIBLE
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