package com.example.roomdb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var firstName: String,
    var lastName: String,
    var cellPhoneNumber: String,
    val studentNumber: String, // Immutable
    var streamOfStudy: String
    // Add other fields as necessary
)
