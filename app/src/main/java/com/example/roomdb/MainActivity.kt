package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roomdb.Fragments.*
import com.example.roomdb.database.AppDatabase
import com.example.roomdb.databinding.ActivityMainBinding
import com.example.roomdb.entities.TodoItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_icon -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.resources_icon -> {
                    loadFragment(ResourcesFragment())
                    true
                }
                R.id.sos_icon -> {
                    loadFragment(SosFragment())
                    true
                }
                R.id.map_icon -> {
                    loadFragment(RoutesFragment())
                    true
                }
                R.id.help_icon -> {
                    loadFragment(HelpFragment())
                    true
                }
                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}


