package com.example.android_app_info_user_random.data.network

import com.example.android_app_info_user_random.data.models.ApiResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getUsers(@Query("results") results: Int = 10): ApiResponseDTO
}