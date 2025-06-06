package com.example.dogs.di

import com.example.database.daos.DogDao
import com.example.di.Container
import com.example.di.Module
import com.example.dogs.data.repositories.DogsRepositoryImpl
import com.example.dogs.domain.repositories.DogsRepository
import com.example.network.service.DogsService

class DogsModule : Module {
    override fun configure(container: Container) {
        // Register DogsRepository
        container.register(DogsRepository::class.java) {
            DogsRepositoryImpl(
                dogsService = container.get(DogsService::class.java),
                dogDao = container.get(DogDao::class.java)
            )
        }
    }
}