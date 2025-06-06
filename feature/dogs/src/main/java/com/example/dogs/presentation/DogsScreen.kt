package com.example.dogs.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.di.inject
import com.example.dogs.presentation.components.DogCard
import com.example.dogs.presentation.viewmodel.DogsViewModel

@Composable
fun DogsScreen(
    modifier: Modifier
) {
    val viewModel = inject<DogsViewModel>()
    val state by viewModel.state.collectAsState()

    Box(
        modifier = modifier
    ) {
        LazyColumn {
            items(state.dogs) { dog ->
                DogCard(dog)
            }
        }
    }
}

@Composable
@Preview
private fun PreviewDogsScreen() {
    MaterialTheme {
        DogsScreen(modifier = Modifier)
    }
}