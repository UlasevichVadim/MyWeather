package com.vadimulasevich.myweather.ui.screen.aboutapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.vadimulasevich.myweather.R
import com.vadimulasevich.myweather.databinding.FragmentAboutAppScreenBinding


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