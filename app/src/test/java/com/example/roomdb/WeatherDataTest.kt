package com.example.roomdb.entities

import org.junit.Test
import org.junit.Assert.*

class WeatherDataTest {

    @Test
    fun `WeatherData should have correct properties`() {
        val weatherData = WeatherData(
            id = 1,
            city = "New York",
            description = "Clear sky",
            temperature = 25.5,
            iconUrl = "https://example.com/icon.png"
        )

        assertEquals(1, weatherData.id)
        assertEquals("New York", weatherData.city)
        assertEquals("Clear sky", weatherData.description)
        assertEquals(25.5, weatherData.temperature, 0.001) // Allowing for a small precision error
        assertEquals("https://example.com/icon.png", weatherData.iconUrl)
    }

    @Test
    fun `WeatherData instances with the same properties should be equal`() {
        val weatherData1 = WeatherData(
            id = 1,
            city = "New York",
            description = "Clear sky",
            temperature = 25.5,
            iconUrl = "https://example.com/icon.png"
        )

        val weatherData2 = WeatherData(
            id = 1,
            city = "New York",
            description = "Clear sky",
            temperature = 25.5,
            iconUrl = "https://example.com/icon.png"
        )

        assertEquals(weatherData1, weatherData2)
    }

    @Test
    fun `WeatherData instances with different properties should not be equal`() {
        val weatherData1 = WeatherData(
            id = 1,
            city = "New York",
            description = "Clear sky",
            temperature = 25.5,
            iconUrl = "https://example.com/icon.png"
        )

        val weatherData2 = WeatherData(
            id = 2,
            city = "Paris",
            description = "Partly cloudy",
            temperature = 20.0,
            iconUrl = "https://example.com/other_icon.png"
        )

        assertNotEquals(weatherData1, weatherData2)
    }
}
