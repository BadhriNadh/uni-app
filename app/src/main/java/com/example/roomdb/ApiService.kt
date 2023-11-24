package com.example.roomdb

import com.example.roomdb.entities.TodoItem
import retrofit2.Response
import retrofit2.http.GET

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface ApiService {
    @GET("todos")
    suspend fun fetchTodoItems(): Response<List<TodoItem>>
}

object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}