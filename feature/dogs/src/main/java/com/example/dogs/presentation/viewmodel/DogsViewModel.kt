package com.example.dogs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.domain.usecases.GetAllDogsUseCase
import com.example.dogs.presentation.state.DogsIntent
import com.example.dogs.presentation.state.DogsUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DogsViewModel(
    private val getAlDogsUseCase: GetAllDogsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(DogsUi())
    val state: StateFlow<DogsUi> = _state.asStateFlow()

    fun handleIntent(intent: DogsIntent) {
        when (intent) {
            is DogsIntent.LoadDogs -> loadDogs()
        }
    }

    private fun loadDogs() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getAlDogsUseCase().collect { dogs ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    dogs = dogs
                )
            }
        }
    }
}