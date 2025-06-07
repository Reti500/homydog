package com.example.dogs.data.repositories

import com.example.core.Resource
import com.example.database.daos.DogDao
import com.example.dogs.data.mappers.MapDogEntityToDog
import com.example.dogs.data.mappers.MapDogResponseToDog
import com.example.dogs.data.mappers.MapDogResponseToEntity
import com.example.dogs.domain.models.Dog
import com.example.dogs.domain.repositories.DogsRepository
import com.example.network.service.DogsService
import com.example.network.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DogsRepositoryImpl(
    private val dogsService: DogsService,
    private val dogDao: DogDao
) : DogsRepository {
    override suspend fun getDogs(): Flow<Resource<List<Dog>>> = flow {
        emit(Resource.Loading)

        try {
            val dogsInDb = dogDao.getAllDogs()

            if (dogsInDb.isNotEmpty()) {
                // Emit dogs if it exists in db
                emit(Resource.Success(dogsInDb.map { MapDogEntityToDog(it) }))
            }

            emit(safeApiCall {
                // Collect dogs from network
                val dogsFromNetwork = dogsService.getDogs()

                // Save dogs in db
                dogDao.insertDogs(dogsFromNetwork.map { MapDogResponseToEntity(it) })

                dogsFromNetwork.map { MapDogResponseToDog(it) }
            })
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error", e))
        }
    }.flowOn(Dispatchers.IO)
}