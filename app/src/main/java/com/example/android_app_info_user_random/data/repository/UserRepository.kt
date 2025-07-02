package com.example.android_app_info_user_random.data.repository

import com.example.android_app_info_user_random.data.network.ApiService

class UserRepository(private val apiService: ApiService) {
    suspend fun fetchUsers() = apiService.getUsers()
}