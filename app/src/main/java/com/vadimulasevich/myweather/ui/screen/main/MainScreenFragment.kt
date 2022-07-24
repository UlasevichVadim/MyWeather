package com.vadimulasevich.myweather.ui.screen.main

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.vadimulasevich.myweather.R
import com.vadimulasevich.myweather.utils.ResultState
import com.vadimulasevich.myweather.databinding.FragmentMainScreenBinding
import com.vadimulasevich.myweather.db.repositories.local.LocationRepository
import com.vadimulasevich.myweather.utils.Constants.LOCATION_PERMISSION_REQ_CODE
import com.vadimulasevich.myweather.utils.PermissionChecker
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapter: WeatherRecyclerDiffAdapter

    private val viewModel: MainScreenViewModel by viewModel()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private fun getCurrentLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        val locationRequest = LocationRequest().setInterval(100).setFastestInterval(100)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {
                        val locationLat = location.latitude
                        val locationLon = location.longitude
                        val locationRepository = LocationRepository()
//                        if(PermissionChecker.hasWriteExternalStoragePermission(requireContext())){
//                            locationRepository.setLocation(latitude = locationLat,
//                                longitude = locationLon)
//                        }else{
//
//                        }
                    }
                }
            },
            Looper.myLooper()
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray,
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_REQ_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    when {
                        PermissionChecker.isLocationEnabled(requireContext()) -> {
                            getCurrentLocation()
                        }
                        else -> {
                            PermissionChecker.showGPSNotEnabledDialog(requireContext())
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), getString(1), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = WeatherRecyclerDiffAdapter(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainScreenBinding.bind(view).apply {
            weatherRecyclerView.adapter = adapter
            weatherRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        when {
            PermissionChecker.checkAccessFineLocationGranted(requireContext()) -> {
                when {
                    PermissionChecker.isLocationEnabled(requireContext()) -> {
                        getCurrentLocation()
                    }
                    else -> {
                        PermissionChecker.showGPSNotEnabledDialog(requireContext())
                    }
                }
            }
            else -> {
                PermissionChecker.askAccessFineLocationPermission(
                    requireActivity() as AppCompatActivity,
                    LOCATION_PERMISSION_REQ_CODE
                )
            }
        }
        viewModel.loadWeather()
        viewModel.localWeatherList.observe(viewLifecycleOwner) {
            when (it) {
                is ResultState.Error -> {
                    binding.progressBarMainScreen.visibility = View.VISIBLE
                    binding.weatherRecyclerView.visibility = View.GONE
                    binding.showMessageErrorOnMainScreen.text = "Error: ${it.throwable.message}"
                    Log.e("SearchScreenFragment", it.throwable.stackTraceToString())
                }
                is ResultState.Loading -> {
                    binding.progressBarMainScreen.visibility = View.VISIBLE
                    binding.weatherRecyclerView.visibility = View.GONE
                    binding.showMessageErrorOnMainScreen.text = "Loading..."
                }
                is ResultState.Success -> {
                    binding.progressBarMainScreen.visibility = View.GONE
                    binding.weatherRecyclerView.visibility = View.VISIBLE
                    binding.showMessageErrorOnMainScreen.visibility = View.GONE
                    adapter.setData(it.data)
                }
            }
        }
    }
}
