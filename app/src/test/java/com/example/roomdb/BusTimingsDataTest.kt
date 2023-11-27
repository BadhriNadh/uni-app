package com.example.roomdb

import com.example.roomdb.busInfo.TimingApiResponse
import com.example.roomdb.entities.Timing
import org.junit.Test
import org.junit.Assert.*

class BusTimingsDataTest {

    @Test
    fun `TimingApiResponse should have correct properties`() {
        val timingList = listOf(
            Timing(1, "101", "10:00"),
            Timing(2, "102", "11:30"),
            Timing(3, "103", "01:00")
        )

        val timingApiResponse = TimingApiResponse(
            status = "success",
            body = timingList
        )

        assertEquals("success", timingApiResponse.status)
        assertEquals(timingList, timingApiResponse.body)
    }

    @Test
    fun `TimingApiResponse instances with the same properties should be equal`() {
        val timingList1 = listOf(
            Timing(1, "101", "10:00"),
            Timing(2, "102", "11:30"),
            Timing(3, "103", "01:00")
        )

        val timingApiResponse1 = TimingApiResponse(
            status = "success",
            body = timingList1
        )

        val timingList2 = listOf(
            Timing(1, "101", "10:00"),
            Timing(2, "102", "11:30"),
            Timing(3, "103", "01:00")
        )

        val timingApiResponse2 = TimingApiResponse(
            status = "success",
            body = timingList2
        )

        assertEquals(timingApiResponse1, timingApiResponse2)
    }

    @Test
    fun `TimingApiResponse instances with different properties should not be equal`() {
        val timingList1 = listOf(
            Timing(1, "101", "10:00"),
            Timing(2, "102", "11:30"),
            Timing(3, "103", "01:00")
        )

        val timingApiResponse1 = TimingApiResponse(
            status = "success",
            body = timingList1
        )

        val timingList2 = listOf(
            Timing(4, "104", "02:30"),
            Timing(5, "105", "04:00")
        )

        val timingApiResponse2 = TimingApiResponse(
            status = "failure",
            body = timingList2
        )

        assertNotEquals(timingApiResponse1, timingApiResponse2)
    }
}