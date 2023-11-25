package com.example.roomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdb.entities.Accessibility
import com.example.roomdb.entities.IncidentReport
import com.example.roomdb.entities.TodoItem

@Database(entities = [TodoItem::class, IncidentReport::class, Accessibility::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoItemDao(): TodoItemDao
    abstract fun incidentReportDao(): IncidentReportDao
    abstract fun accessibilityDao(): AccessibilityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
//                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}