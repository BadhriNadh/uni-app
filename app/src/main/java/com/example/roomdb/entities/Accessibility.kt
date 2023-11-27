package com.example.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Accessibility")
data class Accessibility(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "student_name") val studentName: String,
    @ColumnInfo(name = "student_id") val studentId: String,
    @ColumnInfo(name = "request_type") val requestType: String,
    @ColumnInfo(name = "time") val time: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "purpose") val purpose: String
)
