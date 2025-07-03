package com.example.android_app_info_user_random.data.models

import kotlinx.serialization.Serializable

@Serializable
data class StreetDTO(
    val number: Int,
    val name: String
)
