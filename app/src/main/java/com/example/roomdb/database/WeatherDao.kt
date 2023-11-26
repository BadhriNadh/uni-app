package com.example.roomdb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdb.entities.WeatherData

@Dao
interface WeatherDataDao {
    @Query("SELECT * FROM WeatherData WHERE city = :city")
    suspend fun getWeatherData(city: String): WeatherData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(weatherData: WeatherData)

    @Query("DELETE FROM WeatherData WHERE city = :cityName")
    suspend fun deleteWeatherData(cityName: String)

}
