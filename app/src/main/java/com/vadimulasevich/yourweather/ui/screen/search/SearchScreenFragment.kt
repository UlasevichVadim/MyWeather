package com.vadimulasevich.yourweather.ui.screen.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.vadimulasevich.yourweather.R
import com.vadimulasevich.yourweather.databinding.FragmentSearchScreenBinding


class SearchScreenFragment : Fragment(R.layout.fragment_search_screen) {

    private var _binding: FragmentSearchScreenBinding? = null
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchScreenBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}