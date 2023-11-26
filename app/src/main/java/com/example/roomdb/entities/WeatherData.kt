package com.example.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherData")
data class WeatherData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "temperature") val temperature: Double,
    @ColumnInfo(name = "iconUrl") val iconUrl: String
)
