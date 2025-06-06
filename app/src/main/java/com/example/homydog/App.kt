package com.example.homydog

import android.app.Application
import com.example.di.AppContainer
import com.example.di.Module

class App : Application() {

    val appContainer = AppContainer()

    override fun onCreate() {
        super.onCreate()
        setupDI()
    }

    private fun setupDI() {
        val modules = listOf<Module>()

        modules.forEach { module ->
            module.configure(appContainer)
        }
    }

}