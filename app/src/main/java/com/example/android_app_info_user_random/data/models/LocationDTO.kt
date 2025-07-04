package com.example.android_app_info_user_random.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    val street: StreetDTO,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesDTO,
    val timezone: TimezoneDTO
)
