package com.example.dogs.data.mappers

import com.example.database.entities.DogEntity
import com.example.dogs.domain.models.Dog

object MapDogEntityToDog {
    operator fun invoke(dog: DogEntity): Dog =
        Dog(
            name = dog.name,
            description = dog.description,
            age = dog.age,
            imageUrl = dog.image
        )
}