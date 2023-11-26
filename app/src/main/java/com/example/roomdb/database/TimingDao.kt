package com.example.roomdb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdb.entities.Timing

@Dao
interface TimingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTimings(articles: List<Timing>)

    @Query("SELECT * FROM timing")
    fun getAllTimings(): List<Timing>

    @Query("DELETE FROM timing")
    fun clearTimings()
}