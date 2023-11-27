package com.example.roomdb

import com.example.roomdb.entities.Accessibility
import org.junit.Test
import org.junit.Assert.*

class AccessibilityTest {

    @Test
    fun `Accessibility should have correct properties`() {
        val accessibility = Accessibility(
            id = 1,
            studentName = "John Doe",
            studentId = "12345",
            requestType = "Special Request",
            time = "12:30 PM",
            date = "2023-11-30",
            purpose = "A specific purpose"
        )

        assertEquals(1, accessibility.id)
        assertEquals("John Doe", accessibility.studentName)
        assertEquals("12345", accessibility.studentId)
        assertEquals("Special Request", accessibility.requestType)
        assertEquals("12:30 PM", accessibility.time)
        assertEquals("2023-11-30", accessibility.date)
        assertEquals("A specific purpose", accessibility.purpose)
    }

    @Test
    fun `Accessibility instances with the same properties should be equal`() {
        val accessibility1 = Accessibility(
            id = 1,
            studentName = "John Doe",
            studentId = "12345",
            requestType = "Special Request",
            time = "12:30 PM",
            date = "2023-11-30",
            purpose = "A specific purpose"
        )

        val accessibility2 = Accessibility(
            id = 1,
            studentName = "John Doe",
            studentId = "12345",
            requestType = "Special Request",
            time = "12:30 PM",
            date = "2023-11-30",
            purpose = "A specific purpose"
        )

        assertEquals(accessibility1, accessibility2)
    }

    @Test
    fun `Accessibility instances with different properties should not be equal`() {
        val accessibility1 = Accessibility(
            id = 1,
            studentName = "John Doe",
            studentId = "12345",
            requestType = "Special Request",
            time = "12:30 PM",
            date = "2023-11-30",
            purpose = "A specific purpose"
        )

        val accessibility2 = Accessibility(
            id = 2,
            studentName = "Jane Doe",
            studentId = "54321",
            requestType = "Regular Request",
            time = "1:00 PM",
            date = "2023-12-01",
            purpose = "Another purpose"
        )

        assertNotEquals(accessibility1, accessibility2)
    }
}
