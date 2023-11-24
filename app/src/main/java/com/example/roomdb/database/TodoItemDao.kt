package com.example.roomdb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdb.entities.TodoItem

@Dao
interface TodoItemDao {
    @Query("SELECT * FROM TodoItem")
    suspend fun getAllItems(): List<TodoItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<TodoItem>)

    @Query("DELETE FROM TodoItem")
    suspend fun clearItems()
}