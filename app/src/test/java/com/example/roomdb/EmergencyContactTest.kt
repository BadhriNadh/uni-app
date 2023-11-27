package com.example.roomdb

import com.example.roomdb.Fragments.HelpFrags.EmergencyContact
import org.junit.Test
import org.junit.Assert.*

class EmergencyContactTest {

    @Test
    fun `EmergencyContact should have correct properties`() {
        val emergencyContact = EmergencyContact("John Doe", "1234567890")

        assertEquals("John Doe", emergencyContact.name)
        assertEquals("1234567890", emergencyContact.phoneNumber)
    }

    @Test
    fun `EmergencyContact instances with the same properties should be equal`() {
        val contact1 = EmergencyContact("John Doe", "1234567890")
        val contact2 = EmergencyContact("John Doe", "1234567890")

        assertEquals(contact1, contact2)
    }

    @Test
    fun `EmergencyContact instances with different properties should not be equal`() {
        val contact1 = EmergencyContact("John Doe", "1234567890")
        val contact2 = EmergencyContact("Jane Doe", "9876543210")

        assertNotEquals(contact1, contact2)
    }
}
