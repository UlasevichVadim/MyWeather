package com.vadimulasevich.myweather.ui.screen.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.vadimulasevich.myweather.R
import com.vadimulasevich.myweather.utils.ResultState
import com.vadimulasevich.myweather.databinding.FragmentMainScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainScreenFragment : Fragment(R.layout.fragment_main_screen), KoinComponent {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding
        get() = _binding!!


    private val viewModel: MainScreenViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainScreenBinding.bind(view)
        Log.d("Main", "onViewCreated")

        viewModel.localWeatherList.observe(viewLifecycleOwner) {
            when (it) {
                is ResultState.Error -> {
                    Log.d("Main", "Error onViewCreated")
                    binding.progressBarMainScreen.visibility = View.VISIBLE
                    binding.mainContainer.visibility = View.GONE
                    binding.showMessageErrorOnMainScreen.text = "Error: " + it.throwable.message
                    Log.e("SearchScreenFragment", it.throwable.stackTraceToString())
                }
                is ResultState.Loading -> {
                    Log.d("Main>", "Loading onViewCreated")
                    binding.progressBarMainScreen.visibility = View.VISIBLE
                    binding.mainContainer.visibility = View.GONE
                    binding.showMessageErrorOnMainScreen.text = "Loading..."
                }
                is ResultState.Success -> {
                    Log.d("Main", "Loading onViewCreated")
                    binding.progressBarMainScreen.visibility = View.GONE
                    binding.showMessageErrorOnMainScreen.visibility = View.GONE
                    binding.mainContainer.visibility = View.VISIBLE

                    binding.apply {
//                    address.text = it.addressIn
//                    updatedAt.text = updateAtText
//                    status.text = weatherDescription.capitalize()
//                    temp.text = tempIn
//                    tempMin.text = tempMinIn
//                    tempMax.text = tempMaxIn
//                    sunrise.text =
//                        SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunriseIn * 1000))
//
//                    sunset.text =
//                        SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunsetIn * 1000))
//                    wind.text = windSpeedIn
//                    pressure.text = pressureIn
//                    humidity.text = humidityIn
                    }
                }
            }
        }

    }
}
//                val jsonObj = JSONObject(result)
//                val main = jsonObj.getJSONObject("main")
//                val sys = jsonObj.getJSONObject("sys")
//                val windIn = jsonObj.getJSONObject("wind")
//                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
//                val updateAt = jsonObj.getLong("dt")
//                val updateAtText =
//                    "Update at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
//                        Date(updateAt * 1000))
//                val tempIn = main.getString("temp") + "°C"
//                val tempMinIn = "Min Temp: " + main.getString("temp_min") + "°C"
//                val tempMaxIn = "Max Temp: " + main.getString("temp_max") + "°C"
//                val pressureIn = main.getString("pressure")
//                val humidityIn = main.getString("humidity")
//                val sunriseIn = sys.getLong("sunrise")
//                val sunsetIn = sys.getLong("sunset")
//                val windSpeedIn = windIn.getString("speed")
//                val weatherDescription = weather.getString("description")
//                val addressIn = jsonObj.getString("name") + ", " + sys.getString("country")