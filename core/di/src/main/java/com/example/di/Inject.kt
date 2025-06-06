package com.example.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

val LocalDependenciesContainer = staticCompositionLocalOf<Container> {
    error("No DI Container provided")
}

@Composable
fun ProvideDependenciesContainer(
    container: Container,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalDependenciesContainer provides container, content = content)
}

@Composable
inline fun <reified T> inject(): T {
    val container = LocalDependenciesContainer.current
    return remember { container.get(T::class.java) }
}