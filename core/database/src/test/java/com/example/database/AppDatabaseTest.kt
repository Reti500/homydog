package com.example.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.database.daos.DogDao
import com.example.database.entities.DogEntity
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var dogDao: DogDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        dogDao = db.dogDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    suspend fun `Write and read dogs`() {
        // Act
        val dogs: List<DogEntity> = listOf(
            DogEntity(
                id = 1,
                name = "Test Dog",
                description = "Test Description",
                age = 1,
                image = "Test Image"
            )
        )
        dogDao.insertDogs(dogs)

        val dogsInDb: List<DogEntity> = dogDao.getAllDogs()

        // Assert
        assert(dogsInDb.size == dogs.size)
        assertThat(dogsInDb[0], equalTo(dogs[0]))
    }

}