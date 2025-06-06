package com.example.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dogs")
class DogEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @SerializedName("dogName") var name: String? = null
    @SerializedName("description") var description: String? = null
    @SerializedName("age") var age: Int? = null
    @SerializedName("image") var image: String? = null

}