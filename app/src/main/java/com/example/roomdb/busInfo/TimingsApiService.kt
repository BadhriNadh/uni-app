package com.example.roomdb.busInfo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TimingsApiService {
    @GET("/")
    fun getTiming(
        @Query("time") time: Int,
    ): Call<TimingApiResponse>
}