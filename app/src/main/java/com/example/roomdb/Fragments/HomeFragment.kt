package com.example.roomdb.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomdb.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


import com.example.roomdb.database.AppDatabase
import com.example.roomdb.entities.WeatherData

class HomeFragment : Fragment() {

    private lateinit var weatherCity: TextView
    private lateinit var weatherTemperature: TextView
    private lateinit var weatherDescription: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var database: AppDatabase

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsList: ArrayList<News>
    private lateinit var newsAdaptor: NewsAdaptor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

//        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
        val notificationIcon = view.findViewById<ImageView>(R.id.notification_icon)
        notificationIcon.setOnClickListener {
            val intent = Intent(requireActivity(), NotificationActivity::class.java)
            startActivity(intent)
        }

        val accountIcon = view.findViewById<ImageView>(R.id.account_icon)
        accountIcon.setOnClickListener {
            val intent = Intent(requireActivity(), AccountActivity::class.java)
            startActivity(intent)
        }

        updateNotificationCount()

        recyclerView= view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        newsList=ArrayList()

        newsList.add(News(R.drawable.golden,"Diana’s golden ticket: Dal’s newest Rhodes Scholar is Oxford‑bound on a life‑changing opportunity"))
        newsList.add(News(R.drawable.thinair,"Out of thin air. Dal PhD student wins global research competition"))
        newsList.add(News(R.drawable.holiday," In the spirit of the holidays – a collection of drives and fundraisers at Dal"))
        newsList.add(News(R.drawable.killamsch,"Dal's Killam scholars exemplify where possibility meets impact. New Killam scholars, existing research chairs celebrated at event"))
        newsList.add(News(R.drawable.housing,"The quest for affordable housing: Dal planning prof on the need for new approaches"))
        newsList.add(News(R.drawable.futures,"Expert report imagines possible futures for this ecologically rich slice of Nova Scotia"))

        newsAdaptor=NewsAdaptor(newsList)
        recyclerView.adapter=newsAdaptor

        weatherCity = view.findViewById(R.id.weatherCity)
        weatherTemperature=view.findViewById(R.id.weatherTemperature)
        weatherDescription= view.findViewById(R.id.weatherDescription)
        weatherIcon = view.findViewById(R.id.weatherIcon)

        // Initialize Room database
        database = AppDatabase.getDatabase(requireContext())

        // Fetch weather data and update TextView
        fetchWeatherData("Halifax")

        return view
    }

    private fun fetchWeatherData(city: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = WeatherRetrofitClient.weatherApiService.getWeather(
                    city,
                    "f983ddf08ebfbf4bea08946389df1d0b"
                )

                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    withContext(Dispatchers.Main) {
                        // Update TextView with weather information
                        displayWeatherData(weatherResponse)
                        // Save the weather data in the Room database
                        weatherResponse?.let {
                            saveWeatherDataToDb(it)
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        // If fetching fails, load data from the database
                        loadWeatherDataFromDb(city)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // If fetching fails, load data from the database
                    loadWeatherDataFromDb(city)
                    Log.e("WeatherFetch", "Error fetching weather data: ${e.message}", e)
                }
            }
        }
    }

    private fun saveWeatherDataToDb(weatherResponse: WeatherResponse) {
        // Create a WeatherData object from the API response
        val weatherData = WeatherData(
            0,  // Auto-generated ID
            weatherResponse.name,
            weatherResponse.weather[0].description,
            kelvinToCelsius(weatherResponse.main.temp),
            "https://openweathermap.org/img/wn/${weatherResponse.weather[0].icon}@2x.png"
        )
        GlobalScope.launch(Dispatchers.IO) {
            database.weatherDataDao().deleteWeatherData(weatherResponse.name)
        }
        // Insert or update the weather data in the Room database
        GlobalScope.launch(Dispatchers.IO) {
            database.weatherDataDao().insertOrUpdate(weatherData)
        }
    }

    private fun loadWeatherDataFromDb(city: String) {
        GlobalScope.launch(Dispatchers.IO) {
            // Load the weather data from the Room database
            val weatherData = database.weatherDataDao().getWeatherData(city)

            withContext(Dispatchers.Main) {
                if (weatherData != null) {
                    // Display weather data from the database
                    displayWeatherDataFromDb(weatherData)
                } else {
                    // No weather data available in the database
                    weatherCity.text = "\uD83D\uDCCD"+"Halifax"
                }
            }
        }
    }

    private fun displayWeatherData(weatherResponse: WeatherResponse?) {
        if (weatherResponse != null && weatherResponse.weather.isNotEmpty()) {
            val weather = weatherResponse.weather[0] // Assuming you are interested in the first weather condition
            val temperatureInCelsius = kelvinToCelsius(weatherResponse.main.temp).toInt()

            // Display weather city in weatherCity TextView
            weatherCity.text = "\uD83D\uDCCD"+weatherResponse.name

            // Display weather description in weatherDescription TextView
            weatherDescription.text = weather.description

            // Display temperature in weatherTemperature TextView
            weatherTemperature.text = "$temperatureInCelsius°C"

            // Load and display the weather icon using Glide
            val iconCode = weather.icon
            val iconUrl = "https://openweathermap.org/img/wn/$iconCode@2x.png"
            Glide.with(requireContext())
                .load(iconUrl)
                .into(weatherIcon)
        } else {
            // Handle the case when there is no weather data
            weatherCity.text = "No weather data available"
            weatherDescription.text = ""
            weatherTemperature.text = ""
        }
    }

    private fun displayWeatherDataFromDb(weatherData: WeatherData) {
        val temperatureInCelsius = weatherData.temperature.toInt()

        // Display weather city in weatherCity TextView
        weatherCity.text = "\uD83D\uDCCD"+"${weatherData.city}"

        // Display weather description in weatherDescription TextView
        weatherDescription.text = "${weatherData.description}"

        // Display temperature in weatherTemperature TextView
        weatherTemperature.text = "$temperatureInCelsius°C"

        // Load and display the weather icon using Glide
        Glide.with(requireContext())
            .load(weatherData.iconUrl)
            .into(weatherIcon)
    }


    private fun kelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }
    override fun onResume() {
        super.onResume()
        updateNotificationCount() // Update count when returning to this fragment
    }

    private fun updateNotificationCount() {
        val unreadCount = updateNotificationsCount()
        view?.findViewById<TextView>(R.id.notification_count)?.text = unreadCount.toString()
    }

    companion object {
        var notifications = mutableListOf(
            NotificationItem("Notification 3: Campus will be closed tomorrow due to weather conditions.", false),
            NotificationItem("Notification 2: Your class schedule has been updated.", false),
            NotificationItem("Notification 1: Welcome to MyCampus!", false)
        )

        fun updateNotificationsCount(): Int {
            return notifications.count { !it.isRead }
        }
    }
}

