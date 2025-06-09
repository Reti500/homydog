package com.example.dogs.data.mappers

import com.example.network.response.DogResponse
import org.junit.Test

class MapDogResponseToEntityTest {

    @Test
    fun `All DogResponse fields are non null`() {
        // Arrange
        val response = DogResponse(
            name = "Firulais",
            description = "Un perro muy amigable",
            age = 5,
            image = "http://image.url/firulais.jpg"
        )

        // Act
        val dog = MapDogResponseToEntity(response)

        // Assert
        assert(dog.name == "Firulais")
        assert(dog.description == "Un perro muy amigable")
        assert(dog.age == 5)
        assert(dog.image == "http://image.url/firulais.jpg")
    }

}