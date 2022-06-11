package com.vadimulasevich.yourweather.ui.screen.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vadimulasevich.yourweather.R
import com.vadimulasevich.yourweather.databinding.FragmentSearchScreenBinding
import com.vadimulasevich.yourweather.db.models.Weather


class SearchScreenFragment : Fragment(R.layout.fragment_search_screen) {

    private var _binding: FragmentSearchScreenBinding? = null
    private val binding
        get() = _binding!!


    private lateinit var adapter: WeatherListRecyclerDiffAdapter

    private lateinit var viewModel: SearchScreenViewModel

    private fun initViewModel() {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchScreenViewModel(
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
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchScreenBinding.bind(view)
        initViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}