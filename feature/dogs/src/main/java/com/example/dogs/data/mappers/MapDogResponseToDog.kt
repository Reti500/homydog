package com.example.dogs.data.mappers

import com.example.dogs.domain.models.Dog
import com.example.network.response.DogResponse

object MapDogResponseToDog {
    operator fun invoke(dog: DogResponse): Dog =
        Dog(
            name = dog.name ?: "",
            description = dog.description ?: "",
            age = dog.age ?: 0,
            imageUrl = dog.image ?: ""
        )
}