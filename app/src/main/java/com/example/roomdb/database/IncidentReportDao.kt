package com.example.roomdb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdb.entities.IncidentReport // Change import

@Dao
interface IncidentReportDao {
    @Query("SELECT * FROM IncidentReport") // Change table name
    suspend fun getAllIncidentReports(): List<IncidentReport> // Change return type

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncidentReport(incidentReport: IncidentReport) // Change parameter type

    @Query("DELETE FROM IncidentReport") // Change table name
    suspend fun clearIncidentReports() // Change method name
}