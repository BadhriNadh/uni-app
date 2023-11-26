package com.example.roomdb 

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfeatures.NotificationAdapter
import com.example.myfeatures.NotificationItem
import com.example.myfeatures.R

// Replace with your actual adapter path if necessary

class NotificationActivity : AppCompatActivity(), NotificationAdapter.OnNotificationClickListener {

    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewNotifications)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = NotificationAdapter(MainActivity.notifications).apply {
            notificationClickListener = this@NotificationActivity
        }
        recyclerView.adapter = adapter
    }

    override fun onNotificationClicked(position: Int) {
        // You can update the count here as needed
        MainActivity.notifications[position].isRead = true
        val unreadCount = MainActivity.updateNotificationsCount()
        // Update any UI or shared state as necessary with unreadCount
    }
}