package com.vadimulasevich.yourweather.ui.screen.aboutapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.vadimulasevich.yourweather.R
import com.vadimulasevich.yourweather.databinding.FragmentAboutAppScreenBinding


class AboutAppScreenFragment : Fragment(R.layout.fragment_about_app_screen) {

    private var _binding: FragmentAboutAppScreenBinding? = null
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAboutAppScreenBinding.bind(view)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}