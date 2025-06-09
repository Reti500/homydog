package com.example.dogs.domain.usecases

import com.example.core.Resource
import com.example.dogs.domain.models.Dog
import kotlinx.coroutines.flow.Flow

interface GetAllDogsUseCase {
    suspend operator fun invoke(): Flow<Resource<List<Dog>>>
}