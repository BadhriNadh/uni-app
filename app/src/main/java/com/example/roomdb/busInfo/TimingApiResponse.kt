package com.example.roomdb.busInfo

import com.example.roomdb.entities.Timing

data class TimingApiResponse(
    val status: String,
    val body: List<Timing>
)
