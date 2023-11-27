package com.example.roomdb

import com.example.roomdb.entities.IncidentReport
import org.junit.Test
import org.junit.Assert.*

class IncidentReportTest {

    @Test
    fun `IncidentReport should have correct properties`() {
        val incidentReport = IncidentReport(
            id = 1,
            studentName = "John Doe",
            incidentTime = "12:30 PM",
            incidentDate = "2023-11-30",
            incidentLocation = "School",
            incidentDescription = "A description of the incident"
        )

        assertEquals(1, incidentReport.id)
        assertEquals("John Doe", incidentReport.studentName)
        assertEquals("12:30 PM", incidentReport.incidentTime)
        assertEquals("2023-11-30", incidentReport.incidentDate)
        assertEquals("School", incidentReport.incidentLocation)
        assertEquals("A description of the incident", incidentReport.incidentDescription)
    }

    @Test
    fun `IncidentReport instances with the same properties should be equal`() {
        val report1 = IncidentReport(
            id = 1,
            studentName = "John Doe",
            incidentTime = "12:30 PM",
            incidentDate = "2023-11-30",
            incidentLocation = "School",
            incidentDescription = "A description of the incident"
        )

        val report2 = IncidentReport(
            id = 1,
            studentName = "John Doe",
            incidentTime = "12:30 PM",
            incidentDate = "2023-11-30",
            incidentLocation = "School",
            incidentDescription = "A description of the incident"
        )

        assertEquals(report1, report2)
    }

    @Test
    fun `IncidentReport instances with different properties should not be equal`() {
        val report1 = IncidentReport(
            id = 1,
            studentName = "John Doe",
            incidentTime = "12:30 PM",
            incidentDate = "2023-11-30",
            incidentLocation = "School",
            incidentDescription = "A description of the incident"
        )

        val report2 = IncidentReport(
            id = 2,
            studentName = "Jane Doe",
            incidentTime = "1:00 PM",
            incidentDate = "2023-12-01",
            incidentLocation = "Home",
            incidentDescription = "Another incident"
        )

        assertNotEquals(report1, report2)
    }
}
