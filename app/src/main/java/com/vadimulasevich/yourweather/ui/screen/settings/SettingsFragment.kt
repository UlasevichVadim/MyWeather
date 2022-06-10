package com.vadimulasevich.yourweather.ui.screen.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.vadimulasevich.yourweather.R
import com.vadimulasevich.yourweather.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}