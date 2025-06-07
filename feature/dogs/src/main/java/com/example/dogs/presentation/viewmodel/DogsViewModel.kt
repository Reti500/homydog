package com.example.dogs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Resource
import com.example.dogs.domain.usecases.GetAllDogsUseCase
import com.example.dogs.presentation.state.DogsIntent
import com.example.dogs.presentation.state.DogsUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DogsViewModel(
    private val getAlDogsUseCase: GetAllDogsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(DogsUi())
    val state: StateFlow<DogsUi> = _state.asStateFlow()

    init {
        handleIntent(DogsIntent.LoadDogs)
    }

    fun handleIntent(intent: DogsIntent) {
        when (intent) {
            is DogsIntent.LoadDogs -> loadDogs()
        }
    }

    private fun loadDogs() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getAlDogsUseCase().collect { dogsState ->
                when(dogsState) {
                    Resource.Loading -> _state.update {
                        it.copy(isLoading = true, error = null)
                    }
                    is Resource.Error -> _state.update {
                        it.copy(isLoading = true, error = dogsState.message)
                    }
                    is Resource.Success -> _state.update {
                        it.copy(isLoading = false, dogs = dogsState.data, error = null)
                    }
                }

            }
        }
    }
}