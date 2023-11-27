package com.example.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "IncidentReport")
data class IncidentReport(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "student_name") val studentName: String,
    @ColumnInfo(name = "incident_time") val incidentTime: String,
    @ColumnInfo(name = "incident_date") val incidentDate: String,
    @ColumnInfo(name = "incident_location") val incidentLocation: String,
    @ColumnInfo(name = "incident_description") val incidentDescription: String
)
