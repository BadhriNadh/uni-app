package com.example.roomdb.busInfo

import com.example.roomdb.database.TimingDao
import com.example.roomdb.entities.Timing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TimingRepository(private val timingDao: TimingDao) {
    fun insertTimings(timings: List<Timing>) {
        timingDao.insertTimings(timings)
    }

    suspend fun getAllTimings(): List<Timing> {
        return withContext(Dispatchers.IO) {
            timingDao.getAllTimings()
        }
    }

    fun clearTimings() {
        timingDao.clearTimings()
    }
}