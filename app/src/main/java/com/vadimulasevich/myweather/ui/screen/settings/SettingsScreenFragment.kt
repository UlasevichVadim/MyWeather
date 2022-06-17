package com.vadimulasevich.myweather.ui.screen.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.vadimulasevich.myweather.R

class SettingsScreenFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}