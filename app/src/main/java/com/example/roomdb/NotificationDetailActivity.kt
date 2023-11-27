package com.example.roomdb

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NotificationDetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_detail)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        findViewById<TextView>(R.id.notification_detail_text).text = message
    }

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
    }
}
