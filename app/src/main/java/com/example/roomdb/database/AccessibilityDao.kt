package com.example.roomdb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdb.entities.Accessibility

@Dao
interface AccessibilityDao {
    @Query("SELECT * FROM Accessibility")
    suspend fun getAllAccessibilities(): List<Accessibility>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccessibility(accessibility: Accessibility)

    @Query("DELETE FROM Accessibility")
    suspend fun clearAccessibilities()
}
