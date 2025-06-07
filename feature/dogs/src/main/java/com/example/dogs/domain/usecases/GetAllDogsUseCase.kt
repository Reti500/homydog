package com.example.dogs.domain.usecases

import com.example.core.Resource
import com.example.dogs.domain.models.Dog
import com.example.dogs.domain.repositories.DogsRepository
import kotlinx.coroutines.flow.Flow

class GetAllDogsUseCase(private val dogsRepository: DogsRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Dog>>> =
        dogsRepository.getDogs()
}