package com.vadimulasevich.yourweather.ui.screen.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.vadimulasevich.yourweather.R
import com.vadimulasevich.yourweather.databinding.FragmentSearchScreenBinding
import com.vadimulasevich.yourweather.models.Weather


class SearchScreenFragment : Fragment(R.layout.fragment_search_screen) {

    private var _binding: FragmentSearchScreenBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapter: WeatherListRecyclerDiffAdapter


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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}