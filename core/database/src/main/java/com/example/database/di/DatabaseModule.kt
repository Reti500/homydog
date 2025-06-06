package com.example.database.di

import android.content.Context
import com.example.database.AppDatabase
import com.example.database.daos.DogDao
import com.example.di.Container
import com.example.di.Module

class DatabaseModule(val context: Context) : Module {
    override fun configure(container: Container) {
        container.register(AppDatabase::class.java) {
            AppDatabase.getDatabase(context, "dogs_db") // TODO: get name from config or inject of environment
        }

        container.register(DogDao::class.java) {
            container.get(AppDatabase::class.java).dogDao()
        }
    }
}