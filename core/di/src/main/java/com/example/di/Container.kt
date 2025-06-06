package com.example.di

interface Container {
    fun <T> register(type: Class<T>, factory: () -> T)
    fun <T> get(type: Class<T>): T
}