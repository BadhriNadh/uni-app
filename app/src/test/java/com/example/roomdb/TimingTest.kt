package com.example.roomdb.entities

import org.junit.Test
import org.junit.Assert.*

class TimingTest {

    @Test
    fun `Timing should have correct properties`() {
        val timing = Timing(
            id = 1,
            number = "123",
            time = "12:34 PM"
        )

        assertEquals(1, timing.id)
        assertEquals("123", timing.number)
        assertEquals("12:34 PM", timing.time)
    }

    @Test
    fun `Timing instances with the same properties should be equal`() {
        val timing1 = Timing(
            id = 1,
            number = "123",
            time = "12:34 PM"
        )

        val timing2 = Timing(
            id = 1,
            number = "123",
            time = "12:34 PM"
        )

        assertEquals(timing1, timing2)
    }

    @Test
    fun `Timing instances with different properties should not be equal`() {
        val timing1 = Timing(
            id = 1,
            number = "123",
            time = "12:34 PM"
        )

        val timing2 = Timing(
            id = 2,
            number = "456",
            time = "3:45 PM"
        )

        assertNotEquals(timing1, timing2)
    }
}
