package com.example.dogs.data.mappers

import com.example.database.entities.DogEntity
import com.example.network.response.DogResponse

object MapDogResponseToEntity {
    operator fun invoke(dog: DogResponse): DogEntity =
        DogEntity(
            name = dog.name ?: "",
            description = dog.description ?: "",
            age = dog.age ?: 0,
            image = dog.image ?: ""
        )
}