package com.example.roomdb.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.roomdb.R
import com.example.roomdb.WeatherRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import com.google.gson.Gson
import com.google.gson.GsonBuilder


import com.example.roomdb.WeatherResponse

class HomeFragment : Fragment() {

    private lateinit var weatherText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        weatherText = view.findViewById(R.id.weatherText)

        // Fetch weather data and update TextView
        fetchWeatherData("Halifax")

        return view
    }

    private fun fetchWeatherData(city: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = WeatherRetrofitClient.weatherApiService.getWeather(city, "f983ddf08ebfbf4bea08946389df1d0b")

                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    withContext(Dispatchers.Main) {
                        // Update TextView with weather information
                        displayWeatherData(weatherResponse)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Log.e("WeatherFetch", "Failed to fetch weather data. Response code: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("WeatherFetch", "Error fetching weather data: ${e.message}", e)
                }
            }
        }
    }


    private fun displayWeatherData(weatherResponse: WeatherResponse?) {
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
        val jsonString = gson.toJson(weatherResponse) ?: "No weather data available"
        weatherText.text = jsonString
    }


}
