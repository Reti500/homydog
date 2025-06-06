package com.example.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dogs")
data class DogEntity (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("dogName") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("age") var age: Int,
    @SerializedName("image") var image: String,
)