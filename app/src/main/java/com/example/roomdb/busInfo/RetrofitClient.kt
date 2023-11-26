package com.example.roomdb.busInfo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://irus6bpy32rjiuk27w6luohm2y0vqcoa.lambda-url.us-east-1.on.aws"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpBuilder.getOkHttpClient())
            .build()
    }
}
