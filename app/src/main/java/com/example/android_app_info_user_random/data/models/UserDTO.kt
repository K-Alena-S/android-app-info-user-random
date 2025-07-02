package com.example.android_app_info_user_random.data.models

data class UserDTO(
    val gender: String,
    val name: NameDTO,
    val location: LocationDTO,
    val email: String,
    val login: LoginDTO,
    val dob: DobDTO,
    val registered: RegisteredDTO,
    val phone: String,
    val cell: String,
    val id: IdDTO,
    val picture: PictureDTO,
    val nat: String
)

