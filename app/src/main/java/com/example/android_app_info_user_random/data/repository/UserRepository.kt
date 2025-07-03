package com.example.android_app_info_user_random.data.repository

import android.content.Context
import com.example.android_app_info_user_random.data.models.UserDTO
import com.example.android_app_info_user_random.data.network.ApiService

class UserRepository(private val apiService: ApiService, private val context: Context) {

    suspend fun fetchUsers(forceRefresh: Boolean = false): List<UserDTO> {
        val cachedUsers = ApiService.getUsersFromDataStore(context)
        if (cachedUsers != null && !forceRefresh) {
            return cachedUsers
        }
        val response = runCatching { apiService.getUsers() }
        return response.fold(
            onSuccess = { apiResponse ->
                ApiService.saveUsersToDataStore(context, apiResponse)
                apiResponse.results
            },
            onFailure = { e ->
                throw e
            }
        )
    }
}