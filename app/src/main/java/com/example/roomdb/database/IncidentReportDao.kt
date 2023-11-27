package com.example.roomdb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdb.entities.IncidentReport

@Dao
interface IncidentReportDao {
    @Query("SELECT * FROM IncidentReport")
    suspend fun getAllIncidentReports(): List<IncidentReport>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncidentReport(incidentReport: IncidentReport)

    @Query("DELETE FROM IncidentReport")
    suspend fun clearIncidentReports()
}