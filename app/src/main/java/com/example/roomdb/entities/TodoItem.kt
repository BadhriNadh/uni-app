package com.example.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TodoItem")
data class TodoItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_id")val userId: Int,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "completed")val completed: Boolean
)

