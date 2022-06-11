package com.vadimulasevich.yourweather.ui.screen.main

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.vadimulasevich.yourweather.API_KEY
import com.vadimulasevich.yourweather.CITY
import com.vadimulasevich.yourweather.R
import com.vadimulasevich.yourweather.databinding.FragmentMainScreenBinding
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainScreenBinding.bind(view)
        weatherTask().execute()


    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            binding.apply {
                loader.visibility = View.VISIBLE
                mainContainer.visibility = View.GONE
                errorText.visibility = View.GONE
            }
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API_KEY")
                        .readText(Charsets.UTF_8)
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val windIn = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updateAt = jsonObj.getLong("dt")
                val updateAtText =
                    "Update at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                        Date(updateAt * 1000))
                val tempIn = main.getString("temp") + "°C"
                val tempMinIn = "Min Temp: " + main.getString("temp_min") + "°C"
                val tempMaxIn = "Max Temp: " + main.getString("temp_max") + "°C"
                val pressureIn = main.getString("pressure")
                val humidityIn = main.getString("humidity")
                val sunriseIn = sys.getLong("sunrise")
                val sunsetIn = sys.getLong("sunset")
                val windSpeedIn = windIn.getString("speed")
                val weatherDescription = weather.getString("description")
                val addressIn = jsonObj.getString("name") + ", " + sys.getString("country")

                binding.apply {
                    address.text = addressIn
                    updatedAt.text = updateAtText
                    status.text = weatherDescription.capitalize()
                    temp.text = tempIn
                    tempMin.text = tempMinIn
                    tempMax.text = tempMaxIn
                    sunrise.text =
                        SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunriseIn * 1000))

                    sunset.text =
                        SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunsetIn * 1000))
                    wind.text = windSpeedIn
                    pressure.text = pressureIn
                    humidity.text = humidityIn

                    loader.visibility = View.GONE
                    mainContainer.visibility = View.VISIBLE
                }


            } catch (e: Exception) {
                binding.apply {
                    loader.visibility = View.GONE
                    errorText.visibility = View.VISIBLE
                }
            }
        }

    }
}