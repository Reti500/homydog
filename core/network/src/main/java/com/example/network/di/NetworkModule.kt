package com.example.network.di

import com.example.di.Container
import com.example.di.Module
import com.example.network.BuildConfig
import com.example.network.service.DogsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule(
    private val baseUrl: String
) : Module {
    override fun configure(container: Container) {
        // Register OkHttpClient
        container.register(OkHttpClient::class.java) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }

            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        // Register Retrofit
        container.register(Retrofit::class.java) {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(container.get(OkHttpClient::class.java))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        // Registrar DogService
        container.register(DogsService::class.java) {
            container.get(Retrofit::class.java).create(DogsService::class.java)
        }
    }
}