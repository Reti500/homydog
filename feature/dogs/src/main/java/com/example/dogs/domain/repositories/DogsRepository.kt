package com.example.dogs.domain.repositories

import com.example.dogs.domain.models.Dog
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    suspend fun getDogs(): Flow<List<Dog>>
}