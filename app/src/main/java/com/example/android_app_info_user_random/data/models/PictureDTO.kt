package com.example.android_app_info_user_random.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PictureDTO(
    val large: String,
    val medium: String,
    val thumbnail: String
)
