package com.example.dogs.presentation.state

import com.example.dogs.domain.models.Dog

data class DogsUi(
    val isLoading: Boolean = false,
    val error: String? = null,
    val dogs: List<Dog> = emptyList()
)
