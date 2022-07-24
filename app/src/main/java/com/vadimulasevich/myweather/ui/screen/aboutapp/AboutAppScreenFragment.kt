package com.vadimulasevich.myweather.ui.screen.aboutapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.vadimulasevich.myweather.BuildConfig
import com.vadimulasevich.myweather.R
import com.vadimulasevich.myweather.databinding.FragmentAboutAppScreenBinding
import com.vadimulasevich.myweather.utils.Constants.WEB
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutAppScreenFragment : Fragment(R.layout.fragment_about_app_screen) {

    private var _binding: FragmentAboutAppScreenBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: AboutAppScreenViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAboutAppScreenBinding.bind(view).apply {
            appVersionNumberTextView.text = BuildConfig.VERSION_NAME
            websiteApiTextView.text = WEB
            websiteApiTextView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(WEB)
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}