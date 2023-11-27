package com.example.roomdb

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotificationAdapter(private val notificationList: MutableList<NotificationItem>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    // Define an interface for item click listener
    interface OnNotificationClickListener {
        fun onNotificationClicked(position: Int)
    }


    var notificationClickListener: OnNotificationClickListener? = null


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleView: TextView = itemView.findViewById(R.id.notification_title)
        private var layout: LinearLayout = itemView.findViewById(R.id.notification_layout) // Reference to the layout

        fun bind(notificationItem: NotificationItem, clickListener: OnNotificationClickListener?) {
            titleView.text = notificationItem.message

            // Change the appearance based on read status
            if (notificationItem.isRead) {
                layout.setBackgroundColor(Color.WHITE) // Use white for read notifications
                titleView.setTextColor(Color.GRAY)
            } else {
                layout.setBackgroundColor(Color.LTGRAY) // Use a distinct color for unread notifications
                titleView.setTextColor(Color.BLACK)
            }

            itemView.setOnClickListener {
                notificationItem.isRead = true
                clickListener?.onNotificationClicked(adapterPosition)
                notifyItemChanged(adapterPosition)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notificationList[position]
        holder.bind(notification, notificationClickListener)
        holder.titleView.text = notification.message
        // Set the click listener
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, NotificationDetailActivity::class.java).apply {
                putExtra(NotificationDetailActivity.EXTRA_MESSAGE, notification.message)
            }
            context.startActivity(intent)
            // Mark as read and update the list
            notification.isRead = true
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = notificationList.size

}


