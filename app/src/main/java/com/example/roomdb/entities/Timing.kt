package com.example.roomdb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timing")
data class Timing(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val number: String,
    val time: String
)
