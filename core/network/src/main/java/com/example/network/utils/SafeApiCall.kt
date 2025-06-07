package com.example.network.utils

import com.example.core.Resource
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Resource<T> {
    return try {
        Resource.Success(apiCall())
    } catch (e: HttpException) {
        Resource.Error("Error de red: ${e.code()} ${e.message()}", e)
    } catch (e: IOException) {
        Resource.Error("Error de conexión: ${e.localizedMessage}", e)
    } catch (e: Exception) {
        Resource.Error("Error desconocido: ${e.localizedMessage}", e)
    }
}