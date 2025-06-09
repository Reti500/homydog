package com.example.dogs.data.usecases

import com.example.core.Resource
import com.example.dogs.domain.models.Dog
import com.example.dogs.domain.repositories.DogsRepository
import com.example.dogs.domain.usecases.GetAllDogsUseCase
import kotlinx.coroutines.flow.Flow

class GetAllDogsUseCaseImpl(private val dogsRepository: DogsRepository) : GetAllDogsUseCase {
    override suspend fun invoke(): Flow<Resource<List<Dog>>> =
        dogsRepository.getDogs()
}