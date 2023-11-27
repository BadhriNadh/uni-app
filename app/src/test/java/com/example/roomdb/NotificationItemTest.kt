package com.example.roomdb

import org.junit.Test
import org.junit.Assert.*

class NotificationUnitTest {

    @Test
    fun notification_isReadPropertyUpdatesCorrectly() {
        val notification = NotificationItem(message = "Welcome to MyCampus!", isRead = false)
        assertFalse(notification.isRead)

        notification.isRead = true
        assertTrue(notification.isRead)
    }

    @Test
    fun notification_isUnreadAfterCreation() {
        val notification = NotificationItem(message = "Campus will be closed tomorrow due to weather conditions.", isRead = false)
        assertFalse(notification.isRead)
    }
}