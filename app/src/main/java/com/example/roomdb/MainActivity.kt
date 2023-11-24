package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roomdb.database.AppDatabase
import com.example.roomdb.databinding.ActivityMainBinding
import com.example.roomdb.entities.TodoItem
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TodoAdapter
    private lateinit var database: AppDatabase
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the database
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todo-database"
        ).build()

        loadData()
    }

    private fun loadData() {
        coroutineScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.fetchTodoItems()
                }
                if (response.isSuccessful) {
                    val todoItems = response.body() ?: emptyList()
                    clearAndCacheItems(todoItems)
                    updateRecyclerView(todoItems)
                } else {
//                    showError("API Error: ${response.code()}")
                }
            } catch (e: Exception) {
//                showError("Error: ${e.message}")
                val cachedItems = withContext(Dispatchers.IO) {
                    database.todoItemDao().getAllItems()
                }
                updateRecyclerView(cachedItems)
            }
        }
    }

    private suspend fun clearAndCacheItems(items: List<TodoItem>) {
        withContext(Dispatchers.IO) {
            database.todoItemDao().clearItems()
            database.todoItemDao().insertAll(items)
        }
    }

    private fun updateRecyclerView(items: List<TodoItem>) {
        adapter = TodoAdapter(items)
        recyclerView.adapter = adapter
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}