package com.example.database.di

import android.content.Context
import com.example.database.AppDatabase
import com.example.database.daos.DogDao
import com.example.di.Container
import com.example.di.Module

class DatabaseModule(
    val context: Context,
    val dbName: String
) : Module {
    override fun configure(container: Container) {
        container.register(AppDatabase::class.java) {
            AppDatabase.getDatabase(context, dbName)
        }

        container.register(DogDao::class.java) {
            container.get(AppDatabase::class.java).dogDao()
        }
    }
}