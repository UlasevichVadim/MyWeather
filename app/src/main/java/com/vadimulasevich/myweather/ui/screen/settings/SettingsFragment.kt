package com.vadimulasevich.myweather.ui.screen.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.vadimulasevich.myweather.R
import com.vadimulasevich.myweather.databinding.FragmentSettingsScreenBinding


class SettingsFragment : Fragment(R.layout.fragment_settings_screen) {

    private var _binding: FragmentSettingsScreenBinding? = null
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsScreenBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}