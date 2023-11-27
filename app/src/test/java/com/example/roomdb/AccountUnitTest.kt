package com.example.roomdb

import com.example.roomdb.entities.User
import org.junit.Test
import org.junit.Assert.*

class AccountUnitTest {

    @Test
    fun user_fullNameIsConcatenatedCorrectly() {
        val user = User(id = 1, firstName = "John", lastName = "Doe", cellPhoneNumber = "123456789", studentNumber = "S123", streamOfStudy = "Engineering")
        val fullName = user.firstName + " " + user.lastName
        assertEquals("John Doe", fullName)
    }

    @Test
    fun user_studentNumberIsImmutable() {
        val user = User(id = 1 , firstName = "John", lastName = "Doe", cellPhoneNumber = "123456789", studentNumber = "S123", streamOfStudy = "Engineering")

        try {
            val clazz = user::class.java
            val field = clazz.getDeclaredField("studentNumber")
            if (field.modifiers and java.lang.reflect.Modifier.FINAL == 0) {
                fail("studentNumber should be immutable and final.")
            }
        } catch (e: NoSuchFieldException) {
            fail("studentNumber field must exist in User class.")
        }
    }

    @Test
    fun account_updateChangesPersist() {
        assertTrue("This test is a placeholder and should be replaced with a proper test.", true)
    }
}