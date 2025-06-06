package com.example.homydog

import android.app.Application
import com.example.database.di.DatabaseModule
import com.example.di.AppContainer
import com.example.di.Module
import com.example.dogs.di.DogsModule
import com.example.network.di.NetworkModule

class App : Application() {

    companion object {
        val appContainer = AppContainer()
    }

    override fun onCreate() {
        super.onCreate()
        setupDI()
    }

    private fun setupDI() {
        val modules = listOf<Module>(
            DatabaseModule(this),
            NetworkModule(),
            DogsModule()
        )

        modules.forEach { module ->
            module.configure(appContainer)
        }
    }

}