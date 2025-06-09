package com.example.di

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class AppContainerTest {

    private lateinit var container: AppContainer

    @Before
    fun setup() {
        container = AppContainer()
    }

    @Test
    fun `Register and get a simple dependency`() {
        // Arrange
        val expectedValue = "Test String"

        // Act
        container.register(String::class.java) { expectedValue }
        val result = container.get(String::class.java)

        // Assert
        assertThat("result", result == expectedValue)
    }

    @Test
    fun `Get an unregistered dependency`() {
        // Act
        val exception = assertThrows(IllegalArgumentException::class.java) {
            container.get(String::class.java)
        }

        // Assert
        assertThat("result", exception.message == "${String::class.simpleName} not registered")
    }
}