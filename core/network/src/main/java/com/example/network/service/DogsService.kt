package com.example.network.service

import com.example.network.response.DogResponse
import retrofit2.http.GET

interface DogsService {
    @GET("1151549092634943488")
    suspend fun getDogs(): List<DogResponse>
}