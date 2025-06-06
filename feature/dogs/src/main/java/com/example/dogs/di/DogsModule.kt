package com.example.dogs.di

import com.example.database.daos.DogDao
import com.example.di.Container
import com.example.di.Module
import com.example.dogs.data.repositories.DogsRepositoryImpl
import com.example.dogs.domain.repositories.DogsRepository
import com.example.dogs.domain.usecases.GetAllDogsUseCase
import com.example.dogs.presentation.viewmodel.DogsViewModel
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

        // Register GetAllDogsUseCase
        container.register(GetAllDogsUseCase::class.java) {
            GetAllDogsUseCase(
                dogsRepository = container.get(DogsRepository::class.java)
            )
        }

        // Register DogsViewModel
        container.register(DogsViewModel::class.java) {
            DogsViewModel(
                getAlDogsUseCase = container.get(GetAllDogsUseCase::class.java)
            )
        }
    }
}