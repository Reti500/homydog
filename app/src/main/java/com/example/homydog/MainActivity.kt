package com.example.homydog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.di.ProvideDependenciesContainer
import com.example.dogs.presentation.DogsScreen
import com.example.homydog.ui.theme.HomydogTheme
import com.example.shared.topbar.DefaultTopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomydogTheme {
                ProvideDependenciesContainer(container = App.appContainer) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = { DefaultTopAppBar(getString(R.string.dogs_we_love)) }
                    ) { innerPadding ->
                        DogsScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}