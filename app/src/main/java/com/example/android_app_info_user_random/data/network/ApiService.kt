package com.example.android_app_info_user_random.data.network

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.android_app_info_user_random.data.models.ApiResponseDTO
import com.example.android_app_info_user_random.data.models.UserDTO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException

interface ApiService {
    @GET("api/")
    suspend fun getUsers(@Query("results") results: Int = 20): ApiResponseDTO

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")
        private val USER_DATA_KEY = stringPreferencesKey("user_data")

        suspend fun getUsersFromDataStore(context: Context): List<UserDTO>? {
            return try {
                val jsonString = context.dataStore.data
                    .map { preferences -> preferences[USER_DATA_KEY] ?: "" }
                    .first()
                if (jsonString.isBlank()) null
                else Json.decodeFromString<ApiResponseDTO>(jsonString).results
            } catch (e: IOException) {
                null
            }
        }

        suspend fun saveUsersToDataStore(context: Context, apiResponse: ApiResponseDTO) {
            val jsonString = Json.encodeToString(apiResponse)
            context.dataStore.edit { preferences ->
                preferences[USER_DATA_KEY] = jsonString
            }
        }
    }
}