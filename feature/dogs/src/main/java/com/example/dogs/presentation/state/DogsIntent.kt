package com.example.dogs.presentation.state

sealed class DogsIntent {
    object LoadDogs : DogsIntent()
}