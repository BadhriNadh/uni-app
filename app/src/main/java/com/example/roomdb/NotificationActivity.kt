package com.example.roomdb 

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.Fragments.HomeFragment


class NotificationActivity : AppCompatActivity(), NotificationAdapter.OnNotificationClickListener {

    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewNotifications)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = NotificationAdapter(HomeFragment.notifications).apply {
            notificationClickListener = this@NotificationActivity
        }
        recyclerView.adapter = adapter
    }

    override fun onNotificationClicked(position: Int) {

        HomeFragment.notifications[position].isRead = true
        val unreadCount = HomeFragment.updateNotificationsCount()
    }
}