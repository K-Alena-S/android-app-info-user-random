package com.example.android_app_info_user_random.data.models

data class LoginDTO(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)