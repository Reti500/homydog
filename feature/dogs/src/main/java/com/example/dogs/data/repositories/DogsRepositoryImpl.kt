package com.example.dogs.data.repositories

import com.example.database.daos.DogDao
import com.example.dogs.data.mappers.MapDogEntityToDog
import com.example.dogs.data.mappers.MapDogResponseToDog
import com.example.dogs.data.mappers.MapDogResponseToEntity
import com.example.dogs.domain.models.Dog
import com.example.dogs.domain.repositories.DogsRepository
import com.example.network.service.DogsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DogsRepositoryImpl(
    private val dogsService: DogsService,
    private val dogDao: DogDao
) : DogsRepository {
    override suspend fun getDogs(): Flow<List<Dog>> = flow {
        val dogsInDb = dogDao.getAllDogs()

        if (dogsInDb.isNotEmpty()) {
            // Emit dogs if it exists in db
            emit(dogsInDb.map { MapDogEntityToDog(it) })
        }

        // Collect dogs from network
        val dogsFromNetwork = dogsService.getDogs()

        // Save dogs in db
        dogDao.insertDogs(dogsFromNetwork.map { MapDogResponseToEntity(it) })

        // Emit dogs from network
        emit(dogsFromNetwork.map { MapDogResponseToDog(it) })
    }.flowOn(Dispatchers.IO)
}